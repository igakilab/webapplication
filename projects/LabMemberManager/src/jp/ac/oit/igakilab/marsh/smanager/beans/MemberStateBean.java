package jp.ac.oit.igakilab.marsh.smanager.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;
import jp.ac.oit.igakilab.marsh.smanager.MemberState;
import jp.ac.oit.igakilab.marsh.smanager.StateList;

public class MemberStateBean {
	public static StateList DEFAULT_SLIST = CommonStateSet.LIST;

	private String id;
	private int stateCode;
	private String stateStr;
	private String lastUpdate;

	public MemberStateBean(){
		clear();
	}

	public MemberStateBean(MemberState ms, StateList sl){
		clear();
		setMemberState(ms, sl);
	}

	public String getId(){ return id; }
	public void setId(String i0){ id = i0; }
	public int getStateCode(){ return stateCode; }
	public void setStateCode(int c0){ stateCode = c0; }
	public String getStateStr(){ return stateStr; }
	public void setStateStr(String s0){ stateStr = s0; }
	public String getLastUpdate(){ return lastUpdate; }
	public void setLastUpdate(String s0){ lastUpdate = s0; }


	public void clear(){
		id = "";
		stateCode = CommonStateSet.UNDEFINED;
		stateStr = "UNDEFINED";
		lastUpdate = "";
	}


	public void setMemberState(MemberState ms, StateList sl){
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		id = ms.getId();
		stateCode = ms.getStateCode(sl);
		stateStr = sl.getStateName(stateCode);
		lastUpdate = df.format(ms.getUpdateDate());
	}


	public void setMemberState(MemberState ms){
		setMemberState(ms, DEFAULT_SLIST);
	}
}
