package jp.ac.oit.igakilab.marsh.smanager;

import jp.ac.oit.igakilab.marsh.smanager.records.NameRecordSearcher;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordListManager;

public class MemberStateByname extends MemberState {
	private String name;
	private MemberIdList mlist;

	public MemberStateByname(String n0, MemberIdList m0){
		super(n0);
		name = n0;
		mlist = m0;
	}

	public MemberStateByname(String n0, MemberIdList m0, RecordListManager l0){
		this(n0, m0);
		updateActionRecord(l0);
	}

	public MemberStateByname(String n0, MemberIdList m0, RecordListManager l0, int sl0){
		this(n0, m0);
		updateActionRecord(l0, sl0);
	}

	/*解析用*/
	public void updateActionRecord(RecordListManager manager){
		updateActionRecord(manager, SL_BUFFER);
	}

	public void updateActionRecord(RecordListManager manager, int sl){
		NameRecordSearcher searcher = new NameRecordSearcher(name, mlist);
		switch( sl ){
		case SL_ALL:
			manager.searchRecordList(searcher);
			break;
		case SL_BUFFER:
			manager.searchBufferRecordList(searcher);
			break;
		}
		records = searcher.getRecordList();
		updateHistoryList();
	}

	public void updateActionRecord(RecordList recl){
		NameRecordSearcher searcher = new NameRecordSearcher(name, mlist);
		searcher.init();
		searcher.excute(recl);
		records = searcher.getRecordList();
		updateHistoryList();
	}
}
