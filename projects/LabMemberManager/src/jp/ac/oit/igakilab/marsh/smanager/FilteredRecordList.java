package jp.ac.oit.igakilab.marsh.smanager;



class FilteredRecordList extends RecordList {
	/*コンストラクタ*/
	public FilteredRecordList(){
		super();
	}

	public FilteredRecordList(RecordList l0){
		super(l0);
	}


	/*メソッド*/
	public void takeRecordByName(String name){
		for(int i=(recs.size() - 1); i>=0; i--){
			if( !(recs.get(i).getName().equals(name)) ){
				recs.remove(i);
			}
		}
	}
}
