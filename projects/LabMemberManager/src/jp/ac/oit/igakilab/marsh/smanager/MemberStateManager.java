package jp.ac.oit.igakilab.marsh.smanager;

/*このクラスの機能
 * ・名前とidの参照を管理する
 * ・名前による状態問い合わせについて応答を返却する
 *
 */

public class MemberStateManager {
	/*状態コードの管理*/
	public static final int LOGIN = CommonStateSet.LOGIN;
	public static final int LOGOUT = CommonStateSet.LOGOUT;
	public static final int LECTURE = CommonStateSet.LECTURE;
	public static final int TOILET_1 = CommonStateSet.TOILET_1;
	public static final int TOILET_2 = CommonStateSet.TOILET_2;

	/*デフォルト状態リスト*/
	public static StateList DEFAULT_SLIST= CommonStateSet.LIST;
	public static MemberIdList DEFAULT_MLIST = CommonMemberSet.LIST;


	/*インスタンス変数*/
	private RecordList recs;
	private StateList slist;
	private MemberIdList mlist;


	/*コンストラクタ*/
	public MemberStateManager(){
		recs = new RecordList();
		slist = DEFAULT_SLIST;
		mlist = DEFAULT_MLIST;
	}

	/*レコード追加*/
	public void addMemberState(String id, int code){
		recs.addRecord(new ActionRecord(id, code));
	}



	/* メンバー状態取得 */
	public MemberState getMemberState(String id){
		return new MemberState(id, recs);
	}

	/* メンバー状態取得(ユーザ名) */
	public MemberStateByname getMemberStateByName(String name){
		return new MemberStateByname(name, mlist, recs);
	}


	/* id一覧取得 */
	public String[] getRegistedIdList(){
		return recs.getIdList();
	}



	/* 記録チェック */
	public boolean checkIdRegisted(String id){
		return recs.isIdRegisted(id);
	}

	/* 記録チェック */
	public boolean checkNameRegisted(String name){
		String[] ids = mlist.getIdByName(name);
		boolean flg = true;

		for(int i=0; i<ids.length; i++){
			flg = flg && recs.isIdRegisted(ids[i]);
		}

		return flg;
	}


	/* オブジェクト返し */
	public RecordList getRecordListObject(){
		return recs;
	}
	public StateList getStateListObject(){
		return slist;
	}
	public MemberIdList getMemberIdListObject(){
		return mlist;
	}

}