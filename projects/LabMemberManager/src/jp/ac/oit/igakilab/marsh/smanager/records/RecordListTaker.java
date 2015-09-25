package jp.ac.oit.igakilab.marsh.smanager.records;

public class RecordListTaker extends RecordList {
	/*コンストラクタ*/
	public RecordListTaker(){
		super();
	}

	public RecordListTaker(RecordList l0){
		super(l0);
	}


	/*サーチ用*/
	public void addRecordById(RecordList l0, String id){
		int len = l0.getRecordCount();
		for(int i=0; i<len; i++){
			if( l0.getRecord(i).getId().equals(id) ){
				addRecord(l0.getRecord(i));
			}
		}
	}
}
