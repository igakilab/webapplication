package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.ac.oit.igakilab.marsh.smanager.LogRecorder;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfo;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfoBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberStateManager;
import jp.ac.oit.igakilab.marsh.smanager.StateList;

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


	public MemberInfoBean[] getMemberInfo(){
		MemberInfo[] info = manager.getMemberInfoList();
		MemberInfoBean[] bean = new MemberInfoBean[info.length];
		StateList slist = manager.getStateList();
		int len = info.length;

		for(int i=0; i<len; i++){
			bean[i] = new MemberInfoBean(info[i], slist);
		}

		return bean;
	}


}
