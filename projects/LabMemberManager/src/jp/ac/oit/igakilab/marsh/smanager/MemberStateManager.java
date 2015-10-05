package jp.ac.oit.igakilab.marsh.smanager;

import java.io.FileNotFoundException;
import java.io.IOException;

import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordListManager;

/*このクラスの機能
 * ・名前とidの参照を管理する
 * ・名前による状態問い合わせについて応答を返却する
 *
 */

public class MemberStateManager {
	/*状態コードの管理*/
	public static final int LOGIN = CommonStateSet.LOGIN;
	public static final int LOGIN_5M = CommonStateSet.LOGIN_5M;
	public static final int LOGOUT = CommonStateSet.LOGOUT;
	public static final int LECTURE = CommonStateSet.LECTURE;
	public static final int CONVENI = CommonStateSet.CONVENI;

	/*デフォルト状態リスト*/
	public static StateList DEFAULT_SLIST= CommonStateSet.LIST;
	public static MemberIdList DEFAULT_MLIST = CommonMemberSet.LIST;

	/*設定ファイル*/
	public static String CONF_IDLIST_FILE = "config_idlist.csv";


	/*インスタンス変数*/
	private RecordListManager recs;
	private StateList slist;
	private MemberIdList mlist;


	/*コンストラクタ*/
	public MemberStateManager(){
		init();
	}

	public void init(){
		recs = new RecordListManager();
		slist = DEFAULT_SLIST;
		mlist = new MemberIdList();
		try{
			mlist.importCsvFile(CONF_IDLIST_FILE);
		}catch( FileNotFoundException e0 ){
			mlist = DEFAULT_MLIST;
		}catch( IOException e1 ){}
	}

	/*レコード追加*/
	public void addMemberState(String id, int code){
		recs.addRecord(new ActionRecord(id, code));
	}



	/* メンバー状態取得 */
	public MemberState getMemberState(String id){
		return new MemberState(id, recs.getBufferRecordList());
	}

	/* メンバー状態取得(ユーザ名) */
	public MemberStateByname getMemberStateByName(String name){
		return new MemberStateByname(name, mlist, recs.getBufferRecordList());
	}


	/* id一覧取得 */
	public String[] getRegistedIdList(){
		return recs.getBufferRecordList().getIdList();
	}



	/* 記録チェック */
	public boolean checkIdRegisted(String id){
		return recs.getBufferRecordList().isIdRegisted(id);
	}

	/* 記録チェック */
	public boolean checkNameRegisted(String name){
		String[] ids = mlist.getIdByName(name);
		boolean flg = false;

		for(int i=0; i<ids.length; i++){
			flg = flg || recs.getBufferRecordList().isIdRegisted(ids[i]);
		}

		return flg;
	}


	/* オブジェクト返し */
	public RecordList getRecordListObject(){
		return recs.getBufferRecordList();
	}
	public StateList getStateListObject(){
		return slist;
	}
	public MemberIdList getMemberIdListObject(){
		return mlist;
	}

}