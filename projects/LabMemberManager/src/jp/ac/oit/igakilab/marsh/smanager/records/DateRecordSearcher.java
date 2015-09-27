package jp.ac.oit.igakilab.marsh.smanager.records;

import java.util.Date;

public interface DateRecordSearcher
extends RecordSearcher {
	public static final int DATE_ALL = 401;
	public static final int DATE_LATEST = 402;
	public static final int DATE_PERIOD = 403;

	public int getDatePeriodType();
	public Date getStartDate();
	public Date getEndDate();
	public int getLatestDay();
}
