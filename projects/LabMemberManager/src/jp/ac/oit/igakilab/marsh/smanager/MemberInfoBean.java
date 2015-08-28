package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInfoBean {
	/*定数*/
	public static final int STATE_IN = 101;
	public static final int STATE_OUT = 102;

	/*プライベート定数*/
	static final DateFormat gdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/*インスタンス変数*/
	private String name;
	private int stateCode;
	private String stateStr;
	private String updateDate;
	private String loginDate;


	/*コンストラクタ*/
	public MemberInfoBean(String n0, int s0, String s1, String d0, String d1){
		name = n0;
		stateCode = s0;
		stateStr = s1;
		loginDate= d0;
		updateDate = d1;
	}

	public MemberInfoBean(String n0, int s0, String s1, Date d0, Date d1){
		this(n0, s0, s1, gdf.format(d0), gdf.format(d1));
	}

	public MemberInfoBean(MemberInfo mi, StateList sl){
		this(
			mi.getName(),
			mi.getStateCode(),
			sl.getStateName(mi.getStateCode()),
			gdf.format(mi.getStateUpdateDate(mi.getStateCode())),
			gdf.format(mi.getUpdateDate())
		);
	}

	public MemberInfoBean(String n0, int s0, String d0, String d1){
		this(n0, s0, "", d0, d1);
	}

	public MemberInfoBean(String n0, int s0, Date d0, Date d1){
		this(n0, s0, "", d0, d1);
	}

	public MemberInfoBean(MemberInfo mi){
		this(
			mi.getName(),
			mi.getStateCode(),
			"",
			MemberInfo.getStateList().getStateName(mi.getStateCode()),
			gdf.format(mi.getUpdateDate())
		);
	}

	public MemberInfoBean(){
		this("", 0, "", "", "");
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

	public String getStateStr(){
		return stateStr;
	}
	public void setStateStr(String s0){
		stateStr = s0;
	}

	public String getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(String d0){
		updateDate = d0;
	}
	public void setUpdateDate(Date d0){
		updateDate = gdf.format(d0);
	}

	public String getLoginDate(){
		return loginDate;
	}
	public void setLoginDate(String d0){
		loginDate = d0;
	}
	public void setLoginDate(Date d0){
		loginDate = gdf.format(d0);
	}

	/*デバッグ等用メソッド*/
	public String toString(){
		return String.format("[MI - name:%s, state:%s]",
			name, stateStr
		);
	}
}
