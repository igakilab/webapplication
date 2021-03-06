package jp.ac.oit.igakilab.marsh.smanager.records;

import java.util.Date;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class FixedRecordList extends RecordList {
	public static int DEFAULT_LENGTH = 200;

	private int length;

	public FixedRecordList(){
		super();
		setLength(DEFAULT_LENGTH);
	}

	public FixedRecordList(int l0){
		super();
		setLength(l0);
	}

	public int getLength(){
		return length;
	}

	public void setLength(int l0){
		length = l0;
		normalize();
	}

	public void normalize(){
		int tmp = recs.size();
		while( recs.size() > length &&  length > 0 ){
			recs.remove(recs.size() - 1);
		}
		DebugLog.logm("FixedRecordList", "ListLength " + tmp + " -> " + recs.size() + " normalized");
	}

	public void addRecord(ActionRecord r0){
		if( length > 0 && recs.size() + 1 > length ){
			Date rear_date =  recs.get(recs.size() - 1).getTimeStamp();
			if( rear_date.compareTo(r0.getTimeStamp()) <= 0 ){
				super.addRecord(r0);
				normalize();
			}
		}else{
			super.addRecord(r0);
		}
	}
}
