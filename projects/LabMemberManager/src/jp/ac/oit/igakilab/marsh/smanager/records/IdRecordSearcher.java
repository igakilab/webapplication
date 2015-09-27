package jp.ac.oit.igakilab.marsh.smanager.records;

public class IdRecordSearcher
implements RecordSearcher {
	//フィールド
	private String target_id;
	RecordList list;

	//コンストラクタ
	public IdRecordSearcher(){
		init();
	};

	public IdRecordSearcher(String i0){
		this();
		target_id = i0;
	}

	//初期化
	public void init(){
		target_id = null;
		list = new RecordList();
	}

	//get/set
	public String getTargetId(){
		return target_id;
	}
	public void setTargetId(String i0){
		target_id = i0;
	}

	//オブジェクト取得
	public RecordList getRecordList(){
		return list;
	}

	//リスト操作
	public void clearList(){
		list.clear();
	}

	//実装メソッド
	public boolean isExcutable(){
		return target_id != null;
	}

	public int excute(RecordList r0){
		for(int i=0; i<r0.getRecordCount() && isExcutable(); i++){
			if( !target_id.equals(r0.getRecord(i).getId()) ){
				list.addRecord(r0.getRecord(i));
			}
		}

		return RecordSearcher.EX_CONTINUE;
	}
}
