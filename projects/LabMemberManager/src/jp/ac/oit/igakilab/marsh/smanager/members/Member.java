package jp.ac.oit.igakilab.marsh.smanager.members;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private String name;
	private String password;
	private List<String> convid;

	public Member(){
		init();
	}

	public void init(){
		name = "";
		password = "";
		convid = new ArrayList<String>();
	}

	public String getName(){
		return name;
	}
	public void setName(String n0){
		name = n0;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String p0){
		password = p0;
	}

	public String[] getConvertIds(){
		return convid.toArray(new String[convid.size()]);
	}
	public void addConvertId(String i0){
		convid.add(i0);
	}
}