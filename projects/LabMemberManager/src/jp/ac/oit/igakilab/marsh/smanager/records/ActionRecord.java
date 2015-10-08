package jp.ac.oit.igakilab.marsh.smanager.records;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActionRecord {
	/*スタティックメソッド*/
	public static boolean isTimeout(Date stp, Date now, int time_out){
		long ms_stp = stp.getTime();
		long ms_now = now.getTime();
		long ms_out = time_out * 1000;

		return (time_out > 0) && ((ms_now - ms_stp) > ms_out);
	}


/* ------------------------------------------ */

	/*インスタンス変数*/
	private Date timeStamp;
	private String id;
	private int stateCode;
	private int timeout;


	/*コンストラクタ*/
	public ActionRecord(Date d0, String n0, int c0, int t0){
		timeStamp = d0;
		id = n0;
		stateCode = c0;
		timeout = t0;
	}

	public ActionRecord(Date d0, String n0, int c0){
		this(d0, n0, c0, 0);
	}

	public ActionRecord(String n0, int c0){
		this(Calendar.getInstance().getTime(), n0, c0);
	}

	public ActionRecord(){
		this(Calendar.getInstance().getTime(), "", 0);
	}


	/*メソッド(get/set)*/
	public Date getTimeStamp(){
		return timeStamp;
	}
	public void setTimeStamp(Date d0){
		timeStamp = d0;
	}

	public String getId(){
		return id;
	}
	public void setId(String n0){
		id = n0;
	}

	public int getStateCode(){
		return stateCode;
	}
	public void setStateCode(int c0){
		stateCode = c0;
	}

	public void setTimeout(int t0){
		timeout = t0;
	}
	public int getTimeout(){
		return timeout;
	}

	public boolean isTimeoutSetted(){
		return timeout > 0;
	}

	public boolean isTimeout(Date now){
		return isTimeout(timeStamp, now, timeout);
	}


	/*メソッド(toString)*/
	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("(%s: %s, %d)",
			df.format(timeStamp), id, stateCode);
	}
}
