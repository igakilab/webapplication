package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemberList {
	/*スタティック定数*/
	public static final int STATE_LOGIN = 101;

	/*スタティックメソッド*/
	static StateList initStateList(){
		StateList slist = new StateList();
		slist.addState(new StateInfo(STATE_LOGIN, "LOGIN", 300));

		return slist;
	}


	/*インスタンス変数*/
	List <MemberInfo> mlist;
	StateList slist;
	LogRecorder logrec;


	/*コンストラクタ*/
	public MemberList(){
		mlist = new ArrayList<MemberInfo>();
		slist = initStateList();
	}

	public MemberList(LogRecorder rec){
		this();
		logrec = rec;
		addLog("MEMBERLIST GENERATED");
	}


	/*内部的メソッド*/
	int searchMemberInfoByName(String name){
		for(int i=0; i<mlist.size(); i++){
			if( name.equals(mlist.get(i).getName()) ){
				return i;
			}
		}
		return -1;
	}

	boolean checkMemberTimeout(MemberInfo mi){
		long t_now = Calendar.getInstance().getTimeInMillis();
		long t_update = mi.getUpdateDate().getTime();
		long timeout_inmillis = slist.getStateTimeout(mi.getStateCode()) * 1000;

		return (t_now - t_update) > timeout_inmillis;
	}

	void deleteTimeoutMember(){
		for(int i=(mlist.size() - 1); i>=0; i--){
			if( checkMemberTimeout(mlist.get(i)) ){
				addLog(mlist.get(i).getName() + "is timeout");
				deleteMemberInfo(i);
			}
		}
	}

	void addLog(String msg){
		if( logrec != null ){
			logrec.addSingleLog("MemberList: " + msg, true);
		}
	}

	/*操作メソッド*/
	public void addMemberInfo(MemberInfo mi){
		if( searchMemberInfoByName(mi.getName()) < 0 ){
			addLog("<name:" + mi.getName() + "> added");
			mlist.add(mi);
		}
	}

	public void deleteMemberInfo(int idx){
		if( !(idx < 0 || idx >= mlist.size()) ){
			addLog("<name:" + mlist.get(idx).getName() + "> deleted");
			mlist.remove(idx);
		}
	}

	public void deleteMemberInfo(String name){
		int idx = searchMemberInfoByName(name);
		if( idx >= 0 ){
			deleteMemberInfo(idx);
		}
	}

	public MemberInfo getMemberInfo(int idx){
		if( !(idx < 0 || idx >= mlist.size()) ){
			return mlist.get(idx);
		}
		return null;
	}

	public MemberInfo getMemberInfo(String name){
		int idx = searchMemberInfoByName(name);
		if( idx >= 0 ){
			return mlist.get(idx);
		}else{
			return null;
		}
	}

	public boolean isMemberInfoRegisted(String name){
		return searchMemberInfoByName(name) >= 0;
	}

	public boolean isMemberInfoRegisted(MemberInfo mi){
		return isMemberInfoRegisted(mi.getName());
	}

	public String getStateName(int state_code){
		return slist.getStateName(state_code);
	}

	public void updateMemberList(){
		addLog("MemberList update");
		deleteTimeoutMember();
	}

	/*情報取得メソッド*/
	public void setLogRecorder(LogRecorder recorder){
		logrec = recorder;
	}

	public int getMemberListLength(){
		return mlist.size();
	}

	public StateList getStateList(){
		return slist;
	}
}
