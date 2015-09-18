package jp.ac.oit.igakilab.marsh.smanager;

public class MemberStateByname extends MemberState {
	private String name;
	private MemberIdList mlist;

	public MemberStateByname(String n0, MemberIdList m0){
		super(n0);
		name = n0;
		mlist = m0;
	}

	public MemberStateByname(String n0, MemberIdList m0, RecordList l0){
		this(n0, m0);
		updateActionRecord(l0);
	}

	/*解析用*/
	public void updateActionRecord(RecordList l0){
		RecordListTaker taker = new RecordListTaker();
		String[] ids = mlist.getIdByName(name);

		for(int i=0; i<ids.length; i++){
			taker.addRecordById(l0, ids[i]);
		}

		records = taker;
	}
}
