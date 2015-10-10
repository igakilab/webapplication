package jp.ac.oit.igakilab.marsh.smanager.records;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecordList{
	/*インスタンス変数*/
	private String label;
	protected List<ActionRecord> recs;


	/*コンストラクタ*/
	public RecordList(){
		label = "";
		recs = new ArrayList<ActionRecord>();
	}

	public RecordList(RecordList l0){
		this();
		recs.addAll(l0.getListObject());
	}

	public void init(){
		recs = new ArrayList<ActionRecord>();
	}

	/*操作系メソッド*/
	public void addRecord(ActionRecord ar){
		int i = 0;

		while(
			(i < recs.size()) &&
			(recs.get(i).getTimeStamp().compareTo(ar.getTimeStamp()) > 0)
		){
			i++;
		}

		recs.add(i, ar);
	}

	public ActionRecord getRecord(int idx){
		if( (idx >= 0) && (idx < recs.size()) ){
			return recs.get(idx);
		}else{
			return null;
		}
	}

	public void clear(){
		recs.clear();
	}

	public void setLabel(String l0){
		label = l0;
	}

	public String getLabel(){
		return label;
	}

	/*リスト取得系メソッド*/
	public String[] getIdList(){
		List<String> name_list = new ArrayList<String>();
		String tmp;

		for(int i=0; i<recs.size(); i++){
			tmp = recs.get(i).getId();
			if( name_list.indexOf(tmp) < 0 ){
				name_list.add(tmp);
			}
		}

		return name_list.toArray(new String[0]);
	}

	public boolean isIdRegisted(String name){
		for(int i=0; i<recs.size(); i++){
			if( recs.get(i).getId().equals(name) ){
				return true;
			}
		}
		return false;
	}

	public Date getLastUpdate(){
		if( recs.size() > 0 ){
			return recs.get(0).getTimeStamp();
		}else{
			return null;
		}
	}


	/*情報取得系メソッド*/
	public int getRecordCount(){
		return recs.size();
	}

	public boolean isListEmpty(){
		return recs.size() <= 0;
	}

	public List<ActionRecord> getListObject(){
		return recs;
	}

	public ActionRecord[] toArray(){
		return recs.toArray(new ActionRecord[0]);
	}


	public String toString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lud = isListEmpty() ? ("<ListEmpty>") : (df.format(getLastUpdate()));
		return String.format("[RecordList rows=%d, update=%s]", getRecordCount(), lud);
	}
}