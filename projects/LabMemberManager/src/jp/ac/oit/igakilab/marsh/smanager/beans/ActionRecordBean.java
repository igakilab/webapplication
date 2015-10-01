package jp.ac.oit.igakilab.marsh.smanager.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;

public class ActionRecordBean {
	private String timeStamp;
	private String id;
	private int stateCode;


	public ActionRecordBean(){
		clear();
	}

	public ActionRecordBean(ActionRecord r0){
		clear();
		setActionRecord(r0);
	}


	public String getTimeStamp(){ return timeStamp; }
	public void setTimeStamp(String t0){ timeStamp = t0; }
	public String getId(){ return id; }
	public void setId(String i0){ id = i0; }
	public int getStateCode(){ return stateCode; }
	public void setStateCode(int c0){ stateCode = c0; }


	public void clear(){
		timeStamp = "";
		id = "";
		stateCode = CommonStateSet.UNDEFINED;
	}


	public void setActionRecord(ActionRecord r0){
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		timeStamp = df.format(r0.getTimeStamp());
		id = r0.getId();
		stateCode = r0.getStateCode();
	}
}
