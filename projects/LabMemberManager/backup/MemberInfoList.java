package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class MemberInfoList {
	List<MemberInfo> list;

	public MemberInfoList(){
		list = new ArrayList<MemberInfo>();
	}

	/*メンバーインフォ登録メソッド*/
	public void addMemberInfo(MemberInfo mi){
		if( !checkRegistedByName(mi.getName()) ){
			list.add(mi);
		}
	}

	public void deleteMemberInfo(String name){
		int idx = searchMemberInfoByName(name);
		if( idx >= 0 ){
			list.remove(idx);
		}
	}

	public void addId(String name, String id){
		int idx = searchMemberInfoByName(name);
		if( idx < 0 ){
			MemberInfo mi = new MemberInfo(name);
			mi.addId(id);
			addMemberInfo(mi);
		}else{
			list.get(idx).addId(id);
		}
	}


	public int searchMemberInfoByName(String name){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).getName() == name){
				return i;
			}
		}
		return -1;
	}


	public int searchMemberInfoById(String id){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).checkId(id) ){
				return i;
			}
		}
		return -1;
	}


	public boolean checkRegistedByName(String name){
		return (searchMemberInfoByName(name) < 0);
	}


	public boolean checkRegistedById(String id){
		return (searchMemberInfoById(id) < 0);
	}


	public String[] getMemberNameList(){
		List<String> nlist = new ArrayList<String>();
		for(int i=0; i<nlist.size(); i++){
			nlist.add(list.get(i).getName());
		}
		return list.toArray(new String[0]);
	}


	public MemberInfo getMemberInfo(String name){
		int idx = searchMemberInfoByName(name);
		if( idx < 0 ) return null;

		return list.get(idx);
	}
}