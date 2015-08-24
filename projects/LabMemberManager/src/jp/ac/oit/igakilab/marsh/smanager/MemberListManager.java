package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;
import java.util.Date;

public class MemberListManager {
	static final int STATE_LOGOUT = 101;
	static final int STATE_LOGIN = 102;

	static StateList createStateList(){
		StateList sl = new StateList();
		sl.addState(new StateInfo(STATE_LOGIN, "LOGIN", 300));
		return sl;
	}


	MemberList mlist;
	StateList slist;
	LogRecorder logrec;

	public MemberListManager(){
		mlist = new MemberList();
		slist = createStateList();
		logrec = null;
	}

	public MemberListManager(LogRecorder r0){
		mlist = new MemberList(r0);
		slist = createStateList();
		logrec = r0;
		addLog("Manager Generated");
	}


	/*内部的メソッド*/
	void addLog(String msg){
		if( logrec != null ){
			logrec.addSingleLog("MemberListManager: " + msg, true);
		}
	}


	/*状態操作メソッド*/
	public void addMember(String name, int state_code){
		Date nt;
		MemberInfo nmi;

		if( mlist.isMemberInfoRegisted(name) ){
			setMemberState(name, state_code);
		}else{
			nt = Calendar.getInstance().getTime();
			nmi = new MemberInfo(name, state_code, nt, nt);
			mlist.addMemberInfo(nmi);
			addLog("new member added <name:" + name + ">");
		}
	}

	public void setMemberState(String name, int state_code){
		MemberInfo mi;

		if( mlist.isMemberInfoRegisted(name) ){
			addMember(name, state_code);
		}else{
			mi = mlist.getMemberInfo(name);
			mi.setStateCode(state_code);
			mi.updateDate();
			addLog("member state updated <name:" + name + "> to " + state_code);
		}
	}

	public void deleteMember(String name){
		if( mlist.isMemberInfoRegisted(name) ){
			mlist.deleteMemberInfo(name);
			addLog("member deleted <name:" + name + ">");
		}
	}


	/*情報取得メソッド*/
	public MemberInfo getMemberInfo(int idx){
		return mlist.getMemberInfo(idx);
	}

	public MemberInfo getMemberInfo(String name){
		return mlist.getMemberInfo(name);
	}

	public MemberInfo[] getMemberInfoList(){
		MemberInfo[] mil;
		int llen;

		llen = mlist.getMemberListLength();
		mil = new MemberInfo[llen];

		for(int i=0; i<llen; i++){
			mil[i] = mlist.getMemberInfo(i);
		}

		return mil;
	}


	/*状態コード管理*/
	public StateList getStateList(){
		return slist;
	}

	public String getStateName(int state_code){
		return slist.getStateName(state_code);
	}
}
