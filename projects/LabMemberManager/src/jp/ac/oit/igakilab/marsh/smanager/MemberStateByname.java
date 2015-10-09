package jp.ac.oit.igakilab.marsh.smanager;

import jp.ac.oit.igakilab.marsh.smanager.records.NameRecordSearcher;
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

	/*解析用*/
	public void updateActionRecord(RecordListManager manager){
		NameRecordSearcher searcher = new NameRecordSearcher(name, mlist);
		manager.searchBufferRecordList(searcher);
		records = searcher.getRecordList();
	}
}
