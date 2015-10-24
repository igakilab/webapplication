package jp.ac.oit.igakilab.marsh.smanager.members;

import java.util.ArrayList;
import java.util.List;

public class MemberList {
	List<Member> list;

	public MemberList(){
		init();
	}

	public void init(){
		list = new ArrayList<Member>();
	}

	public int getMemberIdxByName(String name){
		for(int i=0; i<list.size(); i++){
			if( name.equals(list.get(i).getName()) ){
				return i;
			}
		}
		return -1;
	}

	public void addMember(Member m0){
		if( isNameRegisted(m0.getName()) ) return;

		list.add(m0);
	}

	public Member getMember(String name){
		int idx = getMemberIdxByName(name);
		if( idx >= 0 ){
			return list.get(idx);
		}else{
			return null;
		}
	}

	public Member getMemberByIdx(int idx){
		if( idx >= 0 && idx < size() ){
			return list.get(idx);
		}else{
			return null;
		}
	}

	public String[] getNameList(){
		String[] names = new String[list.size()];

		for(int i=0; i<list.size(); i++){
			names[i] = list.get(i).getName();
		}

		return names;
	}

	public boolean isNameRegisted(String name){
		int idx = getMemberIdxByName(name);
		return idx >= 0;
	}

	public int size(){
		return list.size();
	}

	public Member[] toArray(){
		return list.toArray(new Member[list.size()]);
	}
}
