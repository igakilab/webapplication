package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;
import java.util.Date;

public class MemberState {
	public static final int UNDEFINED = CommonStateSet.UNDEFINED;


	/*インスタンス変数*/
	private String id;
	protected RecordList records;

	public MemberState(String i0){
		id = i0;
		records = new RecordList();
	}

	public MemberState(String i0, RecordList l0){
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
	public void updateActionRecord(RecordList l0){
		RecordListTaker taker = new RecordListTaker();
		taker.addRecordById(l0, id);
		records = taker;
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
