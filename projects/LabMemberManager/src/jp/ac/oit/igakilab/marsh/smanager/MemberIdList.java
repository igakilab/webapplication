package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class MemberIdList {
	List<String> identifers;
	List<String> names;


	public MemberIdList(){
		identifers = new ArrayList<String>();
		names = new ArrayList<String>();
	}

	public void addId(String name, String id){
		if( identifers.indexOf(id) < 0 ){
			identifers.add(id);
			names.add(id);
		}
	}


	public String getNameById(String id){
		for(int i=0; i<identifers.size(); i++){
			if( identifers.get(i).equals(id) ){
				return names.get(i);
			}
		}
		return null;
	}


	public String[] getIdByName(String name){
		List<String> tmp = new ArrayList<String>();
		for(int i=0; i<names.size(); i++){
			if( names.get(i).equals(name) ){
				tmp.add(identifers.get(i));
			}
		}
		return tmp.toArray(new String[0]);
	}


	public String[] getRegistedNameList(){
		List<String> name_list = new ArrayList<String>();

		for(int i=0; i<names.size(); i++){
			if( name_list.indexOf(names.get(i)) < 0 ){
				name_list.add(names.get(i));
			}
		}

		return name_list.toArray(new String[0]);
	}

}
