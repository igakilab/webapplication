package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class MemberInfo {
	private String name;
	private List<String> identifers;

	public MemberInfo(String n0){
		name = n0;
		identifers = new ArrayList<String>();
	}


	/*get/set*/
	public String getName(){
		return name;
	}

	public void setName(String n0){
		name = n0;
	}



	public void addId(String id){
		identifers.add(id);
	}



	public boolean checkId(String id){
		return identifers.indexOf(id) >= 0;
	}
}
