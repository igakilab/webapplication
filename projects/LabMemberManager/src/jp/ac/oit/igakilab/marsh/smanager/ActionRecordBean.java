package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionRecordBean {
	private String timeStamp;
	private String name;
	private int stateCode;
	private String stateStr;


	public ActionRecordBean(){
		timeStamp = "";
		name = "";
		stateCode = 0;
		stateStr = "";
	}

	public ActionRecordBean(ActionRecord r0, StateList l0){
		this();
		setTimeStampInDate(r0.getTimeStamp());
		setName(r0.getName());
		setStateCode(r0.getStateCode());
		if( l0 != null ) setStateUseStateList(r0.getStateCode(), l0);
	}


	/*メソッド(get/set)*/
	public String getTimeStamp(){
		return timeStamp;
	}
	public void setTimeStamp(String s0){
		timeStamp = s0;
	}

	public String getName(){
		return name;
	}
	public void setName(String n0){
		name = n0;
	}

	public int getStateCode(){
		return stateCode;
	}
	public void setStateCode(int c0){
		stateCode = c0;
	}

	public String getStateStr(){
		return stateStr;
	}
	public void setStateStr(String s0){
		stateStr = s0;
	}


	/*メソッド*/
	public void setTimeStampInDate(Date ts){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeStamp = df.format(ts);
	}

	public void setStateUseStateList(int code, StateList sl){
		stateCode = code;
		stateStr = sl.getStateName(code);
	}
}
