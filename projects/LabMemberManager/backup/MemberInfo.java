package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;
import java.util.Date;

public class MemberInfo{
	/*スタティック変数*/
	public static final int STATE_LOGIN = 101;
	public static final int STATE_LOGOUT = 102;
	public static final int STATE_UNDEF = 191;

	static StateList STATE_LIST = null;

	/*スタティックメソッド*/
	static boolean checkStateTimeout(Date tstamp, int timeout){
		long t_now = Calendar.getInstance().getTimeInMillis();
		long t_stp = tstamp.getTime();
		long t_out = timeout * 1000;

		return (t_out > 0) && ((t_now - t_stp) > t_out);
	}

	static void initStateList(){
		if( STATE_LIST == null ){
			STATE_LIST = new StateList();
			STATE_LIST.addState(new StateInfo(STATE_LOGIN, "LOGIN", 300));
			STATE_LIST.addState(new StateInfo(STATE_LOGOUT, "LOGOUT", 0));
		}
	}

	public static StateList getStateList(){
		initStateList();
		return STATE_LIST;
	}


	/*インスタンス変数*/
	private String name;
	private ActionRecord[] records;


	/*コンストラクタ*/
	public MemberInfo(String n0, RecordList l0){
		name = n0;
		updateRecordList(l0);
		initStateList();
	}


	/*情報取得(基本)メソッド*/
	public String getName(){
		return name;
	}


	public int getStateCode(){
		int state_code = STATE_UNDEF;

		if( records.length > 0 ){
			state_code = records[0].getStateCode();
		}

		if( STATE_LIST.isStateRegisted(state_code) ){
			if( !checkStateTimeout(records[0].getTimeStamp(), STATE_LIST.getStateTimeout(state_code)) ){
				return state_code;
			}
		}

		return STATE_UNDEF;
	}


	public Date getUpdateDate(){
		if( records.length > 0 ){
			return records[0].getTimeStamp();
		}else{
			return null;
		}
	}


	/*情報取得(応用)メソッド*/
	public Date getStateUpdateDate(int code){
		int i;

		i = 0;
		while( (i < records.length) && (records[i].getStateCode() != code) ){
			i++;
		}

		/*最終まで到達したためアウト*/
		if( i >= records.length ) return null;

		while( (i < records.length) && (records[i].getStateCode() == code) ){
			i++;
		}

		return records[i-1].getTimeStamp();
	}


	/*パラメータ更新*/
	public void updateRecordList(RecordList list){
		FilteredRecordList frl = new FilteredRecordList(list);
		frl.takeRecordByName(name);
		records = frl.toArray();
	}


	/*情報取得(内部的)メソッド*/
	public ActionRecord[] getRecordList(){
		return records;
	}
}
