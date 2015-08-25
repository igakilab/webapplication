package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.LogRecorder;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfo;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfoBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberListManager;

public class LmmManager {
	static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	MemberListManager list;
	LogRecorder logrec;

	/*コンストラクター*/
	public LmmManager(){
		logrec = new LogRecorder("logs.txt", true);
		addLog("Lmmマネージャが開始しました");
		list = new MemberListManager();
	}


	void addLog(String msg){
		logrec.addSingleLog("Lmm: " + msg, true);
	}


	public String login(String name){
		list.setMemberState(name, MemberListManager.STATE_LOGIN);

		return String.format("[%s] logined (now login %d)",
			name, list.getMemberCount()
		);
	}


	public MemberInfoBean[] getMemberList(){
		MemberInfo[] mlist;
		MemberInfoBean[] bean;
		int len;

		mlist = list.getMemberInfoList();
		len = mlist.length;
		bean = new MemberInfoBean[len];

		for(int i=0; i<len; i++){
			bean[i] = new MemberInfoBean(mlist[i], list.getStateList());
		}

		return bean;
	}
}
