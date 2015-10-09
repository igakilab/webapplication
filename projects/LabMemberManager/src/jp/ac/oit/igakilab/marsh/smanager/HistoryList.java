package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;

public class HistoryList {
	public static int UNDEFINED = CommonStateSet.UNDEFINED;


	private List<HistoryRecord> hist;

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
		int code = UNDEFINED;
		int i;

		i = 0;
		while( pt.compareTo(hist.get(i).getTimeStamp()) <= 0 ){
			i++;
			if( i >= hist.size() ) return UNDEFINED;
		}

		return hist.get(i).getStateCode();
	}

	public int getStateCode(){
		return getStateCode(Calendar.getInstance().getTime());
	}

	public void updateHistory(RecordList rec){

	}

}
