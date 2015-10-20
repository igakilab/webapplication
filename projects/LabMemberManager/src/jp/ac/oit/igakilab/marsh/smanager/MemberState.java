package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Date;

import jp.ac.oit.igakilab.marsh.smanager.history.HistoryList;
import jp.ac.oit.igakilab.marsh.smanager.history.HistoryRecord;
import jp.ac.oit.igakilab.marsh.smanager.history.HistoryStatistics;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.IdRecordSearcher;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordListManager;

public class MemberState {
	public static final int UNDEFINED = CommonStateSet.UNDEFINED;


	/*インスタンス変数*/
	private String id;
	protected HistoryList history;
	protected RecordList records;

	public MemberState(String i0){
		init();
		setId(i0);
	}

	public MemberState(String i0, RecordListManager l0){
		this(i0);
		updateActionRecord(l0);
	}

	public void init(){
		id = "";
		records = new RecordList();
	}

	/*get/set*/
	public String getId(){
		return id;
	}

	public void setId(String i0){
		id = i0;
	}


	/*解析用*/
	public void updateActionRecord(RecordListManager l0){
		IdRecordSearcher searcher = new IdRecordSearcher(id);
		l0.searchBufferRecordList(searcher);
		records = searcher.getRecordList();
		updateHistoryList();
	}

	public void updateActionRecord(RecordList l0){
		IdRecordSearcher searcher = new IdRecordSearcher(id);
		searcher.excute(l0);
		records = searcher.getRecordList();
		updateHistoryList();
	}

	public void updateHistoryList(){
		history = new HistoryList(records);
	}


	/*情報取得*/
	public int getStateCode(StateList sl){
		return history.getStateCode();
	}


	public Date getUpdateDate(){
		if( !records.isListEmpty() ){
			return records.getRecord(0).getTimeStamp();
		}
		return null;
	}

	public HistoryStatistics getStatistics(){
		HistoryStatistics stat = new HistoryStatistics();
		stat.analyze(history.toArray());
		return stat;
	}


	/*プロパティ取得*/
	public ActionRecord[] getRecordList(){
		return records.getListObject().toArray(new ActionRecord[0]);
	}

	public HistoryRecord[] getHistoryList(){
		return history.toArray();
	}

	public int getRecordCount(){
		return records.getRecordCount();
	}
}
