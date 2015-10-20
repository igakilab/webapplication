package jp.ac.oit.igakilab.marsh.smanager.beans;

import jp.ac.oit.igakilab.marsh.smanager.history.HistoryStatistics;

public class HistoryStatisticsBean {
	public static HistoryStatisticsBean[] toBeans(HistoryStatistics statistics){
		Integer[] codes = statistics.getStateCodes();
		Integer[] times = statistics.getTotalTimes();
		HistoryStatisticsBean[] beans = new HistoryStatisticsBean[codes.length];

		for(int i=0; i<codes.length; i++){
			beans[i] = new HistoryStatisticsBean(codes[i], times[i]);
		}

		return beans;
	}


	private int code;
	private int totalTime;

	public HistoryStatisticsBean(){
		init();
	}

	public HistoryStatisticsBean(int c0, int t0){
		init();
		setCode(c0);
		setTotalTime(t0);
	}

	public void init(){
		code = 0;
		totalTime = 0;
	}

	public int getCode(){ return code; }
	public void setCode(int c0){ code = c0; }
	public int getTotalTime(){ return totalTime; }
	public void setTotalTime(int t0){ totalTime = t0; }
}
