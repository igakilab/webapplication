package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;
import java.util.Date;

import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.IdRecordSearcher;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordListManager;

public class MemberState {
	public static final int UNDEFINED = CommonStateSet.UNDEFINED;


	/*インスタンス変数*/
	private String id;
	protected RecordList records;

	public MemberState(String i0){
		id = i0;
		records = new RecordList();
	}

	public MemberState(String i0, RecordListManager l0){
		this(i0);
		updateActionRecord(l0);
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
	}


	/*情報取得*/
	public int getStateCode(StateList sl){
		ActionRecord last;

		if( !records.isListEmpty() ){
			last = records.getRecord(0);

			if( !sl.checkStateTimeout(
				last.getStateCode(),
				last.getTimeStamp(),
				Calendar.getInstance().getTime()
			) ){
				return last.getStateCode();
			}
		}

		return UNDEFINED;
	}


	public Date getUpdateDate(){
		if( !records.isListEmpty() ){
			return records.getRecord(0).getTimeStamp();
		}
		return null;
	}


	/*プロパティ取得*/
	public ActionRecord[] getRecordList(){
		return records.getListObject().toArray(new ActionRecord[0]);
	}

	public int getRecordCount(){
		return records.getRecordCount();
	}
}
