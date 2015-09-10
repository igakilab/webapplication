package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.ac.oit.igakilab.marsh.smanager.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.ActionRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.LogRecorder;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfo;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfoBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberStateManager;

public class LmmManager {
	static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	MemberStateManager manager;
	LogRecorder logrec;

	/*コンストラクター*/
	public LmmManager(){
		logrec = new LogRecorder("logs.txt", true);
		addLog("Lmmマネージャが開始しました");
		manager = new MemberStateManager();
	}


	void addLog(String msg){
		logrec.addSingleLog("Lmm: " + msg, true);
	}



	/*操作*/
	public String login(String name){
		manager.addMemberState(name, MemberStateManager.STATE_LOGIN);
		return "[" + name + "] login (" + DF.format(Calendar.getInstance().getTime()) + ")";
	}


	public String logout(String name){
		manager.addMemberState(name, MemberStateManager.STATE_LOGOUT);
		return "[" + name + "] logout(" + DF.format(Calendar.getInstance().getTime()) + ")";
	}


	public MemberInfoBean getMemberInfo(String name){
		MemberInfo minf = manager.getMemberInfo(name);
		if( minf == null ){
			return null;
		}
		MemberInfoBean bean = new MemberInfoBean(minf);

		return bean;
	}


	public MemberInfoBean[] getMemberList(){
		MemberInfo[] mlist = manager.getMemberInfoList();
		MemberInfoBean[] bean = new MemberInfoBean[mlist.length];
		for(int i=0; i<mlist.length; i++){
			bean[i] = new MemberInfoBean(mlist[i]);
		}
		return bean;
	}


	public ActionRecordBean[] getActionRecordList(){
		ActionRecord[] raw = manager.getRecordListObject().toArray();
		ActionRecordBean[] bean = new ActionRecordBean[raw.length];

		for(int i=0; i<raw.length; i++){
			bean[i] = new ActionRecordBean(raw[i], MemberInfo.getStateList());
		}
		return bean;
	}


	public ActionRecordBean[] getMemberActionRecordList(String name){
		ActionRecord[] raw = manager.getMemberInfo(name).getRecordList();
		ActionRecordBean[] bean = new ActionRecordBean[raw.length];

		for(int i=0; i<raw.length; i++){
			bean[i] = new ActionRecordBean(raw[i], MemberInfo.getStateList());
		}
		return bean;
	}
}
