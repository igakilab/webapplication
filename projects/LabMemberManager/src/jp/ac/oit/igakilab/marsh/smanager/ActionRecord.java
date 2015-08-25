package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActionRecord {
	private Date timeStamp;
	private String name;
	private int stateCode;


	/*コンストラクタ*/
	public ActionRecord(String n0, int c0){
		name = n0;
		stateCode = c0;
		timeStamp = Calendar.getInstance().getTime();
	}


	/*メソッド(get/set)*/
	public Date getTimeStamp(){
		return timeStamp;
	}
	public void setTimeStamp(Date d0){
		timeStamp = d0;
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


	/*メソッド(デバッグ用)*/
	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return String.format("[%s %s,%d]",
			df.format(timeStamp), name, stateCode);
	}
}
