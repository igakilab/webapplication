package jp.ac.oit.igakilab.marsh.smanager.records;

public interface RecordSearcher {
	public ActionRecord[] excute(RecordList recs);
	public boolean isExcutable();
}