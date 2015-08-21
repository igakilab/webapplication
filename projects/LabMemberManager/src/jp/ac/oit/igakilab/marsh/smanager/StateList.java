package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StateList {
	public static final int STATE_IN = MemberInfo.STATE_IN;
	public static final int STATE_OUT = MemberInfo.STATE_OUT;


	/*インスタンス変数*/
	ArrayList<MemberInfo> list;


	/*コンストラクタ*/
	StateList(){
		list = new ArrayList<MemberInfo>();
	}


	/*内部メソッド*/
	int searchIndexByName(String n0){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).getName().equals(n0) ){
				return i;
			}
		}

		return -1;
	}


	/*メソッド*/
	public void addMember(String name, int state){
		int idx = searchIndexByName(name);
		MemberInfo tmp;

		if( idx < 0 ){
			list.add(new MemberInfo(name, state));
		}else{
			tmp = list.get(idx);
			tmp.setState(state);
			tmp.setUpdateDateNow();
		}
	}


	public void deleteMember(String name){
		int idx = searchIndexByName(name);

		if( idx >= 0 ){
			list.remove(idx);
		}
	}


	public void deleteMember(int idx){
		if( idx >= 0 || idx < list.size() ){
			list.remove(idx);
		}
	}


	public int getMemberNum(){
		return list.size();
	}


	public MemberInfo getMemberInfo(int idx){
		if( idx < 0 || idx >= list.size() ){
			return null;
		}else{
			return list.get(idx);
		}
	}


	public MemberInfo getMemberInfoByName(String name){
		int idx = searchIndexByName(name);

		if( idx < 0 ){
			return null;
		}else{
			return list.get(idx);
		}
	}


	public void printList(){
		MemberInfo inf;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		System.out.format("SIZE = %d\n", list.size());
		System.out.format("NAME\tSTATE\tDATE\n");

		for(int i=0; i<list.size(); i++){
			inf = list.get(i);

			System.out.print(inf.getName() + "\t");

			switch( inf.getState() ){
				case MemberInfo.STATE_IN:
					System.out.print("SIGNIN");
					break;
				default:
					System.out.print("UNDEFINED");
					break;
			}
			System.out.print("\t");
			System.out.println(df.format(inf.getUpdateDate().getTime()));
		}
	}
}
