package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.ac.oit.igakilab.marsh.smanager.MemberState;
import jp.ac.oit.igakilab.marsh.smanager.MemberStateManager;
import jp.ac.oit.igakilab.marsh.smanager.StateInfo;
import jp.ac.oit.igakilab.marsh.smanager.beans.ActionRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.HistoryRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.HistoryStatisticsBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.MemberStateBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.RecordListContainer;
import jp.ac.oit.igakilab.marsh.smanager.history.HistoryStatistics;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;

public class LmmManager {
	static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	MemberStateManager manager;


	public LmmManager(){
		init();
	}

	public void init(){
		manager = new MemberStateManager();
	}



	/*メンバー状態操作*/
	public String login(String name){
		manager.addMemberState(name, MemberStateManager.LOGIN_5M);
		return "[" + name + "] login (" + DF.format(Calendar.getInstance().getTime()) + ")";
	}

	public String logout(String name){
		manager.addMemberState(name, MemberStateManager.LOGOUT);
		return "[" + name + "] logout(" + DF.format(Calendar.getInstance().getTime()) + ")";
	}

	public String registState(String id, int code){
		ActionRecord ar = manager.addMemberState(id, code);
		ActionRecordBean bean = new ActionRecordBean(ar);
		String s_ar = "Time:" + bean.getTimeStamp() + ", Id:" + bean.getId() +
				", Code:" + bean.getStateCode() + ", DeadLine:" + bean.getDeadLine();
		return "(" + s_ar + ") registed";
	}


	/*レコード取得, 登録id, name確認*/
	public ActionRecordBean[] getBufferRecordList(){
		return ActionRecordBean.toBeans(manager.getBufferRecordList().toArray());
	}

	public ActionRecordBean[] getAllRecordList(){
		return ActionRecordBean.toBeans(manager.getAllRecordList().toArray());
	}

	//互換性維持
	public ActionRecordBean[] getRecordList(){
		return getBufferRecordList();
	}

	public RecordListContainer[] getAllRecordLists(){
		RecordList[] recs = manager.getAllRecordLists();
		RecordListContainer[] containers = new RecordListContainer[recs.length];
		RecordListContainer.sortRecordLists(recs);
		for(int i=0; i<recs.length; i++){
			containers[i] = new RecordListContainer(recs[i]);
		}
		return containers;
	}

	public String[] getRegistedId(){
		return manager.getRegistedIdList();
	}

	public String[] getMemberNameList(){
		return manager.getMemberList();
	}


	/*メンバー個別情報取得*/
	public MemberStateBean getMemberState(String name){
		MemberState ms = manager.getMemberState(name, false);
		return new MemberStateBean(ms, manager.getStateListObject());
	}

	public ActionRecordBean[] getMemberRecordList(String name){
		MemberState ms = manager.getMemberState(name, true);
		return ActionRecordBean.toBeans(ms.getRecordList());
	}

	public HistoryRecordBean[] getHistoryRecord(String name){
		MemberState ms = manager.getMemberState(name, true);
		return HistoryRecordBean.toBeans(ms.getHistoryList());
	}

	public HistoryStatisticsBean[] getHistoryStatistics(String name){
		MemberState ms = manager.getMemberState(name, true);
		HistoryStatistics stat = ms.getStatistics();
		return HistoryStatisticsBean.toBeans(stat);
	}


	/*その他操作*/
	public String loadAllRecordsToBuffer(){
		manager.getRecordListManager().loadAllRecordsToBuffer();
		int cnt = manager.getBufferRecordList().getRecordCount();

		return "load all records to buffer (" + cnt + " record(s)!)";
	}

	public void loadConfigs(){
		manager.loadConfigs();
	}

	public StateInfo[] getStateList(){
		return manager.getStateListObject().toStateListArray();
	}
}
