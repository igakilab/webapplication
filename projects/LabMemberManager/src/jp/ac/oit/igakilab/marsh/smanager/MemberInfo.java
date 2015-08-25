package jp.ac.oit.igakilab.marsh.smanager;

import java.util.Calendar;
import java.util.Date;

public class MemberInfo{
	/*スタティック変数*/
	public static final int STATE_UNDEFINED = 8101;


	/*スタティックメソッド*/
	static boolean checkStateTimeout(StateList slist, int code, Date tstamp){
		long t_out = slist.getStateTimeout(code) * 1000;
		long t_now = Calendar.getInstance().getTimeInMillis();
		long t_stp = tstamp.getTime();

		return (
			(t_out > 0) &&
			((t_now - t_stp) > t_out)
		);
	}


	/*インスタンス変数*/
	private String name;
	private ActionRecord[] records;

	public MemberInfo(String n0, RecordList l0){
		name = n0;
		updateRecordList(l0);
	}


	/*情報取得(基本)メソッド*/
	public String getName(){
		return name;
	}


	public int getStateCode(StateList slist){
		int tmp;

		for(int i=0; i<records.length; i++){
			tmp = records[i].getStateCode();
			if( slist.isStateRegisted(tmp) ){
				if( !checkStateTimeout(slist, tmp, records[i].getTimeStamp()) ){
					return tmp;
				}
			}
		}

		return STATE_UNDEFINED;
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
		records = list.getRecordListByName(name);
	}


	/*情報取得(内部的)メソッド*/
	public ActionRecord[] getRecordList(){
		return records;
	}
}
