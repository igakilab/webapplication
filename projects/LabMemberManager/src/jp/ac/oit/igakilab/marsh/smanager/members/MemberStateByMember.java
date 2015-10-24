package jp.ac.oit.igakilab.marsh.smanager.members;

import jp.ac.oit.igakilab.marsh.smanager.MemberState;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordListManager;

public class MemberStateByMember extends MemberState {
	Member minfo;

	public MemberStateByMember(Member m0){
		super(m0.getName());
		minfo = m0;
	}

	public MemberStateByMember(Member m0, RecordList r0){
		this(m0);
		updateActionRecord(r0);
	}

	public MemberStateByMember(Member m0, RecordListManager mg0){
		this(m0);
		updateActionRecord(mg0);
	}

	public void updateActionRecord(RecordList r0){
		MemberRecordSearcher searcher = new MemberRecordSearcher(minfo);
		searcher.init();
		searcher.excute(r0);
		records = new RecordList(searcher.getRecordList());
		updateHistoryList();
	}

	public void updateActionRecord(RecordListManager mg0){
		updateActionRecord(mg0, SL_BUFFER);
	}

	public void updateActionRecord(RecordListManager mg0, int sl){
		MemberRecordSearcher searcher = new MemberRecordSearcher(minfo);
		switch( sl ){
		case SL_ALL:
			mg0.searchRecordList(searcher);
			break;
		case SL_BUFFER:
			mg0.searchBufferRecordList(searcher);
			break;
		}
		records = new RecordList(searcher.getRecordList());
		updateHistoryList();
	}
}
