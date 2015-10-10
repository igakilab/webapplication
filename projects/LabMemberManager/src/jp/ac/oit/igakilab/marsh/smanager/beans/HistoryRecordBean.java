package jp.ac.oit.igakilab.marsh.smanager.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.history.HistoryRecord;

public class HistoryRecordBean {
	public static HistoryRecordBean[] toBeans(HistoryRecord[] records){
		HistoryRecordBean[] beans = new HistoryRecordBean[records.length];
		for(int i=0; i<records.length; i++){
			beans[i] = new HistoryRecordBean(records[i]);
		}
		return beans;
	}



	private String timeStamp;
	private int stateCode;

	public HistoryRecordBean(){
		timeStamp = "";
		stateCode = 0;
	}

	public HistoryRecordBean(HistoryRecord hr0){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		timeStamp = df.format(hr0.getTimeStamp());
		stateCode = hr0.getStateCode();
	}

	public void setTimeStamp(String s0){ timeStamp = s0; }
	public String getTimeStamp(){ return timeStamp; }
	public void setStateCode(int c0){ stateCode = c0; }
	public int getStateCode(){ return stateCode; }
}
