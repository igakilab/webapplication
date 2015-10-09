package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryRecord {
	private Date timeStamp;
	private int stateCode;

	public HistoryRecord(Date d0, int c0){
		setTimeStamp(d0);
		setStateCode(c0);
	}

	public void setTimeStamp(Date d0){ timeStamp = d0; }
	public Date getTimeStamp(){ return timeStamp; }
	public void setStateCode(int c0){ stateCode = c0; }
	public int getStateCode(){ return stateCode; }

	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "[time: " + df.format(timeStamp) + ", code: " + stateCode + "]";
	}
}
