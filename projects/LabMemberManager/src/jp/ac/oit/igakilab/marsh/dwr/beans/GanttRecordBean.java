package jp.ac.oit.igakilab.marsh.dwr.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.dwr.lmm.GanttRecord;

public class GanttRecordBean {
	public static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static GanttRecordBean[] toBeans(GanttRecord[] recs){
		GanttRecordBean[] beans = new GanttRecordBean[recs.length];
		for(int i=0; i<recs.length; i++){
			beans[i] = new GanttRecordBean(recs[i]);
		}
		return beans;
	}

/* - - - - - */
	private String label;
	private String dateFrom;
	private long dateFromSec;
	private String dateTo;
	private long dateToSec;

	public GanttRecordBean(){
		label = "";
		dateFrom = "";
		dateFromSec = 0;
		dateTo = "";
		dateToSec = 0;
	}

	public GanttRecordBean(GanttRecord grec){
		this();
		toBean(grec);
	}

	public void toBean(GanttRecord grec){
		label = grec.getLabel();
		if( grec.getStartDate() != null ){
			dateFrom = DF.format(grec.getStartDate());
			dateFromSec = (grec.getStartDate().getTime()/1000);
		}
		if( grec.getEndDate() != null ){
			dateTo = DF.format(grec.getEndDate());
			dateToSec = (grec.getEndDate().getTime()/1000);
		}
	}

	public String getLabel(){ return label; }
	public void setLabel(String l0){ label = l0; }
	public String getDateFrom(){ return dateFrom; }
	public void setDateFrom(String f0){ dateFrom = f0; }
	public long getDateFromSec(){ return dateFromSec; }
	public void setDateFromSec(int sf0){ dateFromSec = sf0; };
	public String getDateTo(){ return dateTo; }
	public void setDateTo(String t0){ dateTo = t0; }
	public long getDateToSec(){ return dateToSec; }
	public void setDateToSec(int sf0){ dateToSec = sf0; };
}
