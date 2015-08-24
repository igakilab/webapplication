package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemberInfo{
	/*定数*/
	public static final int STATE_IN = 101;
	public static final int STATE_OUT = 102;


	/*インスタンス変数*/
	String name;
	Calendar update_date;
	int state;


	/*コンストラクタ*/
	MemberInfo(String n0, int s0, Calendar d0){
		name = n0;
		state = s0;
		update_date = d0;
	}

	MemberInfo(String n0, int s0){
		this(n0, s0, Calendar.getInstance());
	}

	MemberInfo(String n0){
		this(n0, STATE_IN, Calendar.getInstance());
	}


	/*メソッド*/
	public String getName(){
		return new String(name);
	}

	public void setName(String n0){
		name = n0;
	}

	public int getState(){
		return state;
	}

	public void setState(int s0){
		state = s0;
	}

	public Calendar getUpdateDate(){
		return update_date;
	}

	public void setUpdateDate(Calendar c0){
		update_date = c0;
	}

	public void setUpdateDateNow(){
		update_date = Calendar.getInstance();
	}

	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return String.format("%s (%d, %s)",
			name,
			state,
			df.format(update_date)
		);
	}
}
