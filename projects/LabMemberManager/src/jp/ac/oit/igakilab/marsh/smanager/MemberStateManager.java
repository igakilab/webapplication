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


	/*インスタンス変数*/
	private RecordList recs;
	private StateList slist;


	/*コンストラクタ*/
	public MemberStateManager(){
		recs = new RecordList();
		slist = DEFAULT_SLIST;
	}

	/*メソッド*/
	public void addMemberState(String id, int code){
		recs.addRecord(new ActionRecord(id, code));
	}


	public MemberState getMemberState(String id){
		return new MemberState()
	}







}