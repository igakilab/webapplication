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

	MemberState getMemberStateObject(String name){
		MemberState ms;
		if( manager.checkNameRegisted(name) ){
			ms = manager.getMemberStateByName(name);
		}else{
			ms = manager.getMemberState(name);
		}
		return ms;
	}

	/*謫堺ｽ�*/
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


	public MemberStateBean getMemberState(String name){
		MemberState ms = getMemberStateObject(name);
		return new MemberStateBean(ms, manager.getStateListObject());
	}


	public String[] getRegistedId(){
		return manager.getRegistedIdList();
	}


	public String[] getMemberNameList(){
		return manager.getMemberIdListObject().getRegistedNameList();
	}


	public ActionRecordBean[] getRecordList(){
		ActionRecord[] recs = manager.getRecordListObject().toArray();
		ActionRecordBean[] beans = new ActionRecordBean[recs.length];

		for(int i=0; i<recs.length; i++){
			beans[i] = new ActionRecordBean(recs[i]);
		}

		return beans;
	}


	public ActionRecordBean[] getMemberRecordList(String name){
		MemberState ms = getMemberStateObject(name);
		ActionRecord[] recs;
		ActionRecordBean[] beans;

		recs = ms.getRecordList();
		beans = new ActionRecordBean[recs.length];

		for(int i=0; i<recs.length; i++){
			beans[i] = new ActionRecordBean(recs[i]);
		}

		return beans;
	}


	public ActionRecordBean[] getAllRecordList(){
		ActionRecord[] recs = manager.getAllRecordList().toArray();
		ActionRecordBean[] beans = new ActionRecordBean[recs.length];

		for(int i=0; i<recs.length; i++){
			beans[i] = new ActionRecordBean(recs[i]);
		}

		return beans;
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

	public String loadAllRecordsToBuffer(){
		manager.getRecordListManager().loadAllRecordsToBuffer();
		int cnt = manager.getRecordListObject().getRecordCount();

		return "load all records to buffer (" + cnt + " record(s)!)";
	}



	public HistoryRecordBean[] getHistoryRecord(String name){
		MemberState ms = getMemberStateObject(name);

		return HistoryRecordBean.toBeans(ms.getHistoryList());
	}

	public HistoryStatisticsBean[] getHistoryStatistics(String name){
		MemberState ms = getMemberStateObject(name);
		HistoryStatistics stat = ms.getStatistics();

		return HistoryStatisticsBean.toBeans(stat);
	}

	public StateInfo[] getStateList(){
		return manager.getStateListObject().toStateListArray();
	}
}
