package jp.ac.oit.igakilab.marsh.smanager.records;

public class IdRecordSearcher
implements RecordSearcher {
	//フィールド
	private String target_id;

	//コンストラクタ
	public IdRecordSearcher(){};

	public IdRecordSearcher(String i0){
		target_id = i0;
	}

	//get/set
	public String getTargetId(){
		return target_id;
	}
	public void setTargetId(String i0){
		target_id = i0;
	}

	//実装メソッド
	public boolean isExcutable(){
		return target_id != null;
	}

	public ActionRecord[] excute(RecordList r0){
		RecordList list = new RecordList();

		for(int i=0; i<r0.getRecordCount() && isExcutable(); i++){
			if( !target_id.equals(r0.getRecord(i).getId()) ){
				list.addRecord(r0.getRecord(i));
			}
		}

		return list.toArray();
	}
}
