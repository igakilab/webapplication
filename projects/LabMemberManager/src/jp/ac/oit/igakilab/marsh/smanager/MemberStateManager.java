package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class MemberStateManager {
	/*スタティック変数*/
	public static final int STATE_LOGIN = 101;
	public static final int STATE_LOGOUT = 102;
	public static final int STATE_UNDEF = MemberInfo.STATE_UNDEFINED;


	/*スタティック関数*/
	StateList createStateList(){
		StateList sl = new StateList();
		sl.addState(new StateInfo(STATE_LOGIN, "LOGIN", 300));
		sl.addState(new StateInfo(STATE_LOGOUT, "LOGOUT", 0));

		return sl;
	}

	/*インスタンス変数*/
	RecordList records;
	StateList slist;


	/*コンストラクタ*/
	public MemberStateManager(){
		records = new RecordList();
		slist = createStateList();
	}


	/*メソッド*/
	public void addMemberState(String name, int code){
		records.addRecord(new ActionRecord(name, code));
	}


	public String[] getMemberNameList(){
		List<String> mem_list = new ArrayList<String>();
		ActionRecord[] rec_list = records.getRecordListArray();
		String tmp;
		int n;

		for(int i=0; i<rec_list.length; i++){
			tmp = rec_list[i].getName();

			n = 0;
			while( n < mem_list.size() ){
				if( mem_list.get(n).equals(tmp) ){
					break;
				}
				n++;
			}

			if( n >= mem_list.size() ){
				mem_list.add(tmp);
			}
		}

		return mem_list.toArray(new String[0]);
	}


	public MemberInfo[] getMemberInfoList(){
		MemberInfo[] mem_list;
		String[] name_list;
		int list_len;

		name_list = getMemberNameList();
		list_len = name_list.length;
		mem_list = new MemberInfo[list_len];

		for(int i=0; i<list_len; i++){
			mem_list[i] =
				new MemberInfo(name_list[i], records);
		}

		return mem_list;
	}


	public int getRegistedMemberCount(){
		return getMemberNameList().length;
	}


	public StateList getStateList(){
		return slist;
	}
}
