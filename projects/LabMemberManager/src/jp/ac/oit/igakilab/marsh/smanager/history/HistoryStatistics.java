package jp.ac.oit.igakilab.marsh.smanager.history;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;

public class HistoryStatistics {
	public static int UNDEFINED = CommonStateSet.UNDEFINED;

	static Date dateToSecDate(Date d0){
		long sec = d0.getTime() / 1000;
		return new Date(sec * 1000);
	}

	static int getDateSpan(Date d0, Date d1){
		long sec0 = d0.getTime() / 1000;
		long sec1 = d1.getTime() / 1000;
		return (int)Math.abs(sec1 - sec0);
	}


	List<Integer> stateCodes;
	List<Integer> totalTimes;


	public HistoryStatistics(){
		init();
	}

	public void init(){
		stateCodes = new ArrayList<Integer>();
		totalTimes = new ArrayList<Integer>();
	}

	void addTime(int code, int time){
		int idx = stateCodes.indexOf(code);
		int tmp;

		if( idx < 0 ){
			stateCodes.add(code);
			totalTimes.add(time);
		}else{
			tmp = totalTimes.get(idx);
			totalTimes.set(idx, tmp + time);
		}
	}

	public void analyze(HistoryRecord[] records, Date begin, Date end){
		Date pre_ts = null;
		int pre_sc = UNDEFINED;
		int cnt = 0;

		stateCodes.clear();
		totalTimes.clear();

		while( begin.compareTo(records[cnt].getTimeStamp()) > 0 ) cnt++;
		if( cnt > 0 ){
			pre_sc = records[cnt - 1].getStateCode();
		}
		pre_ts = begin;

		while( end.compareTo(records[cnt].getTimeStamp()) > 0 && cnt < records.length ){
			addTime(pre_sc, getDateSpan(pre_ts, records[cnt].getTimeStamp()));
			pre_sc = records[cnt].getStateCode();
			pre_ts = records[cnt].getTimeStamp();
			cnt++;
		}

		addTime(pre_sc, getDateSpan(pre_ts, end));
	}

	public void analyze(HistoryRecord[] records, Date end){
		analyze(records, records[0].getTimeStamp(), end);
	}

	public void analyze(HistoryRecord[] records){
		analyze(records, Calendar.getInstance().getTime());
	}

	public Integer[] getStateCodes(){
		return stateCodes.toArray(new Integer[stateCodes.size()]);
	}

	public Integer[] getTotalTimes(){
		return totalTimes.toArray(new Integer[totalTimes.size()]);
	}
}