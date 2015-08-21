package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemberInfoBean {
	/*定数*/
	public static final int STATE_IN = 101;
	public static final int STATE_OUT = 102;


	/*インスタンス変数*/
	private String name;
	private int state;
	private Date update;


	/*コンストラクタ*/
	public MemberInfoBean(String n0, int s0, Date d0){
		name = n0;
		state = s0;
		update = d0;
	}

	public MemberInfoBean(String n0, int s0){
		this(n0, s0, Calendar.getInstance().getTime());
	}

	public MemberInfoBean(MemberInfo mic){
		this(mic.getName(), mic.getState(), mic.getUpdateDate().getTime());
	}


	/*メソッド(get/set)*/
	public String getName(){
		return name;
	}

	public int getState(){
		return state;
	}

	public Date getUpdate(){
		return update;
	}

	public void setName(String n0){
		name = n0;
	}

	public void setState(int s0){
		state = s0;
	}

	public void setUpdate(Date d0){
		update = d0;
	}


	/*メソッド*/
	public String getUpdateString(){
		DateFormat df = new SimpleDateFormat("yyy-MM-dd hh:mm;ss");
		return df.format(update);
	}
}
