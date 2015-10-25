package jp.ac.oit.igakilab.marsh.smanager;

import jp.ac.oit.igakilab.marsh.smanager.members.MemberList;
import jp.ac.oit.igakilab.marsh.smanager.members.MemberStateByMember;
import jp.ac.oit.igakilab.marsh.smanager.members.XmlMemberList;
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

	/*設定ファイル*/
	public static String CONF_MEMBER_FILE = "config/members.xml";


	/*インスタンス変数*/
	private RecordListManager recs;
	private StateList slist;
	private XmlMemberList mlist;


	/*コンストラクタ*/
	public MemberStateManager(){
		init();
	}

	public void init(){
		recs = new RecordListManager();
		slist = DEFAULT_SLIST;
		mlist = new XmlMemberList();
		mlist.loadXmlFile(CONF_MEMBER_FILE);
	}

	/*レコード追加*/
	public ActionRecord addMemberState(String id, int code){
		ActionRecord new_act = new ActionRecord(id, code, slist.getStateTimeout(code));
		recs.addRecord(new_act);
		return new_act;
	}



	/* メンバー状態取得 */
	public MemberState getMemberState(String name, boolean all){
		int search_level;

		if( all ){
			search_level = MemberState.SL_ALL;
		}else{
			search_level = MemberState.SL_BUFFER;
		}

		if( mlist.isNameRegisted(name) ){
			return new MemberStateByMember(mlist.getMember(name), recs, search_level);
		}else{
			return new MemberState(name, recs, search_level);
		}
	}


	/* id一覧取得 */
	public String[] getRegistedIdList(){
		return recs.getBufferRecordList().getIdList();
	}



	/* 記録チェック */
	public boolean checkIdRegisted(String id){
		return recs.getBufferRecordList().isIdRegisted(id);
	}

	public boolean checkNameRegisted(String name){
		return mlist.isNameRegisted(name);
	}

	public RecordList getBufferRecordList(){
		return recs.getBufferRecordList();
	}

	public RecordList getAllRecordList(){
		return recs.getAllRecordList();
	}

	public RecordList[] getAllRecordLists(){
		return recs.getAllRecordLists();
	}


	/* オブジェクト返し */
	public RecordListManager getRecordListManager(){
		return recs;
	}
	public StateList getStateListObject(){
		return slist;
	}
	public MemberList getMemberListObject(){
		return mlist;
	}

}