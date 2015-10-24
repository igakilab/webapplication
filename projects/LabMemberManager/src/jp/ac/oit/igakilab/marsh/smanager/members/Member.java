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

	public Member(String n0){
		init();
		setName(n0);
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
	public boolean deleteConvertId(String i0){
		int idx = convid.indexOf(i0);
		if( idx >= 0 ){
			convid.remove(idx);
			return true;
		}else{
			return false;
		}
	}
}