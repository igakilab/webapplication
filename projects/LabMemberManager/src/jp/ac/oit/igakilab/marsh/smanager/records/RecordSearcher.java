package jp.ac.oit.igakilab.marsh.smanager.records;

public interface RecordSearcher {
	static public int EX_BREAK = 301;
	static public int EX_CONTINUE = 302;
	public int excute(RecordList recs);
	public boolean isExcutable();
}