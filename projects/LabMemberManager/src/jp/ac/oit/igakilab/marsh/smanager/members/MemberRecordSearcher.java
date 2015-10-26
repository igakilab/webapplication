package jp.ac.oit.igakilab.marsh.smanager.members;

import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordSearcher;

public class MemberRecordSearcher implements RecordSearcher {
	Member minfo;
	RecordList records;
	List<String> ids;

	public MemberRecordSearcher(Member m0){
		minfo = m0;
		init();
	}

	@Override
	public void init() {
		records = new RecordList();
		ids = new ArrayList<String>();

		String[] conv_id = minfo.getConvertIds();
		for(int i=0; i<conv_id.length; i++){
			ids.add(conv_id[i]);
		}
		ids.add(minfo.getName());
	}

	@Override
	public int excute(RecordList recs) {
		int len = recs.getRecordCount();

		for(int i=0; i<len; i++){
			if( ids.contains(recs.getRecord(i).getId()) ){
				records.addRecord(recs.getRecord(i));
			}
		}

		return MemberRecordSearcher.EX_CONTINUE;
	}

	@Override
	public boolean isExcutable() {
		return minfo != null && records != null && ids != null;
	}

	public RecordList getRecordList(){
		return records;
	}
}
