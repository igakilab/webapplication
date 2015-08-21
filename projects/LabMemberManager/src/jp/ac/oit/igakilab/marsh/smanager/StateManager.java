package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;

public class StateManager extends StateList{
	static final long TIMEOUT = 300 * 1000;

	public void updateStateList(){
		int length;
		long t_now, t_update;

		length = getMemberNum();
		for(int i=length - 1; i>=0; i--){
			t_update = getMemberInfo(i).getUpdateDate().getTime().getTime();
			t_now = Calendar.getInstance().getTime().getTime();

			if( (t_now - t_update) > TIMEOUT ){
				deleteMember(i);
			}
		}
	}

	public void addMember(String name, int state){
		super.addMember(name, state);
	}
}
