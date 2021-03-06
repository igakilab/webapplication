package jp.ac.oit.igakilab.marsh.smanager.history;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;

public class HistoryList {
	public static int UNDEFINED = CommonStateSet.UNDEFINED;

/* -------------------------------*/

	private List<HistoryRecord> hist;

	public HistoryList(){
		init();
	}

	public HistoryList(RecordList r0){
		init();
		updateHistory(r0);
	}

	public void init(){
		hist = new ArrayList<HistoryRecord>();
	}

	public HistoryRecord getHistoryRecord(int idx){
		if( idx >= 0 && idx < hist.size() ){
			return hist.get(idx);
		}else{
			return null;
		}
	}

	public int getListLength(){
		return hist.size();
	}

	public int getStateCode(Date pt){
		int i;

		i = hist.size() - 1;
		while( i >= 0 ){
			if( pt.compareTo(hist.get(i).getTimeStamp()) > 0 ){
				return hist.get(i).getStateCode();
			}
			i--;
		}

		return UNDEFINED;
	}

	public int getStateCode(){
		return getStateCode(Calendar.getInstance().getTime());
	}

	public void updateHistory(RecordList rec){
		int pre_statecode = UNDEFINED;
		Date pre_deadline = null;
		ActionRecord tmp;
		int rec_len = rec.getRecordCount();

		hist.clear();
		if( rec_len - 1 >= 0 ){
			tmp = rec.getRecord(rec_len - 1);
			hist.add(new HistoryRecord(tmp.getTimeStamp(), tmp.getStateCode()));
			pre_statecode = tmp.getStateCode();
			pre_deadline = tmp.getTimeoutDate();
		}else{
			return;
		}

		for(int i=rec_len-2; i>=0; i--){
			tmp = rec.getRecord(i);
			if( pre_deadline != null && pre_deadline.compareTo(tmp.getTimeStamp()) < 0 ){
				hist.add(new HistoryRecord(pre_deadline, UNDEFINED));
				hist.add(new HistoryRecord(tmp.getTimeStamp(), tmp.getStateCode()));
			}else{
				if( pre_statecode != tmp.getStateCode() ){
					hist.add(new HistoryRecord(tmp.getTimeStamp(), tmp.getStateCode()));
				}
			}
			pre_statecode = tmp.getStateCode();
			pre_deadline = tmp.getTimeoutDate();
		}

		if( pre_deadline != null ){
			hist.add(new HistoryRecord(pre_deadline, UNDEFINED));
		}

	}

	public HistoryRecord[] toArray(){
		return hist.toArray(new HistoryRecord[hist.size()]);
	}
}
