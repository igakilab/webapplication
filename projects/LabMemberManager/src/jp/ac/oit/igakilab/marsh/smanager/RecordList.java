package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordList {
	/*インスタンス変数*/
	List<ActionRecord> list;


	/*コンストラクタ*/
	public RecordList(){
		list = new ArrayList<ActionRecord>();
	}


	/*メソッド*/
	public void addRecord(ActionRecord rec){
		int i;

		i = 0;
		while(
			(i < list.size()) &&
			(list.get(i).getTimeStamp().compareTo(rec.getTimeStamp()) < 0)
		){
			i++;
		}

		list.add(i, rec);
	}


	public ActionRecord getRecord(int idx){
		if( idx >= 0 && idx < list.size() ){
			return list.get(idx);
		}else{
			return null;
		}
	}


	public int getRecordListLength(){
		return list.size();
	}


	/*メソッド リスト取得*/
	public ActionRecord[] getRecordListArray(){
		return list.toArray(new ActionRecord[0]);
	}


	public List<ActionRecord> getRecordList(){
		return list;
	}


	public ActionRecord[] getRecordListByName(String name){
		List<ActionRecord> hit = new ArrayList<ActionRecord>();

		for(int i=0; i<list.size(); i++){
			if( list.get(i).getName().equals(name) ){
				hit.add(list.get(i));
			}
		}

		return hit.toArray(new ActionRecord[0]);
	}


	public ActionRecord[] getRecordListByRecentTime(Date time){
		List<ActionRecord> hit = new ArrayList<ActionRecord>();

		for(int i=0; i<list.size(); i++){
			if( list.get(i).getTimeStamp().compareTo(time) >= 0 ){
				hit.add(list.get(i));
			}
		}

		return hit.toArray(new ActionRecord[0]);
	}
}
