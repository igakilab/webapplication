package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.LogRecorder;
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


	public void login(String name){
		if( list.setMemberStaten)
	}
}
