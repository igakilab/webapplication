package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.history.HistoryRecord;

public class GanttConverter {
	static int UNDEFINED = 191;

	List<Integer> codes;
	List<String> labels;
	List<GanttRecord> grecs;

	public GanttConverter(){
		init();
	}

	public void init(){
		codes = new ArrayList<Integer>();
		labels = new ArrayList<String>();
		grecs = new ArrayList<GanttRecord>();
	}

	public void addState(int code, String label){
		if( !codes.contains(code) ){
			codes.add(code);
			labels.add(label);
		}
	}

	public boolean containsCode(int code){
		return codes.contains(code);
	}

	public String getLabel(int code){
		int idx = codes.indexOf(code);
		if( idx >= 0 ){
			return labels.get(idx);
		}else{
			return "UNDEF";
		}
	}

	public void analyze(HistoryRecord[] records){
		if( records.length > 0 ) analyze(records, records[0].getTimeStamp());
	}

	public void analyze(HistoryRecord[] records, Date begin){
		analyze(records, begin, Calendar.getInstance().getTime());
	}

	public void analyze(HistoryRecord[] records, Date begin, Date end){
		Date pre_ts = null;
		int pre_sc = UNDEFINED;
		int cnt = 0;

		grecs.clear();

		while( cnt < records.length && begin.compareTo(records[cnt].getTimeStamp()) > 0 ) cnt++;
		if( cnt > 0 ){
			pre_sc = records[cnt - 1].getStateCode();
		}
		pre_ts = begin;

		while( cnt < records.length && end.compareTo(records[cnt].getTimeStamp()) > 0 ){
			if( containsCode(pre_sc) ){
				grecs.add(new GanttRecord(getLabel(pre_sc), pre_ts, records[cnt].getTimeStamp()));
			}
			pre_sc = records[cnt].getStateCode();
			pre_ts = records[cnt].getTimeStamp();
			cnt++;
		}

		if( containsCode(pre_sc) ){
			grecs.add(new GanttRecord(getLabel(pre_sc), pre_ts, end));
		}
	}

	public GanttRecord[] getGanttRecords(){
		return grecs.toArray(new GanttRecord[grecs.size()]);
	}
}
