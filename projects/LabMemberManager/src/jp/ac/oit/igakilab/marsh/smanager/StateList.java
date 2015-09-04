package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class StateList {
	/*定数*/
	public static final int STATE_NOTFOUND = 8001;

	/*インスタンス変数*/
	List<StateInfo> list;


	/*コンストラクタ*/
	StateList(){
		list = new ArrayList<StateInfo>();
	}


	/*メソッド*/
	public void addState(StateInfo si){
		for(int i=0; i<list.size(); i++){
			if( si.getName().equals(list.get(i).getName()) ){
				return;
			}
		}

		list.add(si);
	}

	public int searchStateInfo(int code){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).getCode() == code){
				return i;
			}
		}

		return -1;
	}

	public String getStateName(int code){
		int idx = searchStateInfo(code);

		if( idx >= 0 ){
			return list.get(idx).getName();
		}else{
			return "UNDEFINED";
		}
	}

	public int getStateTimeout(int code){
		int idx = searchStateInfo(code);

		if( idx >= 0){
			return list.get(idx).getTimeout();
		}else{
			return -1;
		}
	}

	public boolean isStateRegisted(int code){
		return searchStateInfo(code) >= 0;
	}


	/*デバッグ等用メソッド*/
	void clearStateList(){
		list.clear();
	}

	int getStateListLength(){
		return list.size();
	}

	String[] getStateListString(){
		int len = list.size();
		String slist[] = new String[len];

		for(int i=0; i<len; i++){
			slist[i] = list.get(i).toString();
		}

		return slist;
	}
}
