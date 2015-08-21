package jp.ac.oit.igakilab.marsh.smanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StateManager {
	/*インスタンス変数*/
	StateList list;

	/*定数*/
	public static final int STATE_IN = StateList.STATE_IN;
	public static final int STATE_OUT = StateList.STATE_OUT;
	static final int TIMEOUT = 600;

	/*コンストラクタ*/
	StateManager(){
		list = new StateList();
	}

	/*メソッド*/
	public void setMemberState(String name, int state){
		list.addMember(name, state);
	}

	public List<String> getMemberList(){
		int length;
		MemberInfo inf_tmp;
		List<String> li = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		length = list.getMemberNum();

		for(int i=0; i<length; i++){
			inf_tmp = list.getMemberInfo(i);
			li.add(
				String.format("%s(%d. %s)",
					inf_tmp.getName(),
					inf_tmp.getState(),
					df.format(inf_tmp.getUpdateDate().getTime())
				)
			);
		}

		return li;
	}

	public updateStateList(){
		int length;
		MemberInfo inf_tmp;

		for(int i=0; i<length; i++){
			inf_tmp = list.getMemberInfo(i);
			inf_tmp.getUpdateDate().compareTo(Calendar.getInstance());

		}
	}
}
