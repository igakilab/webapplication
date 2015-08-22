package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemberInfo{
	/*インスタンス変数*/
	String name;
	int stateCode;
	Date loginDate;
	Date updateDate;


	/*コンストラクタ*/
	public MemberInfo(String n0, int s0, Date d0, Date d1){
		setName(n0);
		setStateCode(s0);
		setLoginDate(d0);
		setUpdateDate(d1);
	}

	public MemberInfo(String n0, int s0){
		Date now = Calendar.getInstance().getTime();

		setName(n0);
		setStateCode(s0);
		setLoginDate(now);
		setUpdateDate(now);
	}


	/*メソッド(get/set)*/
	public String getName(){
		return name;
	}
	public void setName(String n0){
		name = n0;
	}

	public int getStateCode(){
		return stateCode;
	}
	public void setStateCode(int s0){
		stateCode = s0;
	}

	public Date getLoginDate(){
		return loginDate;
	}
	public void setLoginDate(Date d0){
		loginDate = d0;
	}

	public Date getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(Date d0){
		updateDate = d0;
	}


	/*メソッド*/
	public void updateDate(){
		updateDate = Calendar.getInstance().getTime();
	}


	/*デバッグ用等メソッド*/
	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return String.format("[MI name:%s, state:%d, update:%s]",
				name, stateCode, df.format(updateDate));
	}
}
