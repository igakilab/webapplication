package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.util.Date;

public class GanttRecord {
	private String label;
	private Date startDate;
	private Date endDate;

	public GanttRecord(){
		label = "";
		startDate = null;
		endDate = null;
	}

	public GanttRecord(String l0, Date s0, Date e0){
		this();
		setLabel(l0);
		setStartDate(s0);
		setEndDate(e0);
	}

	public String getLabel(){ return label; }
	public void setLabel(String l0){ label = l0; }
	public Date getStartDate(){ return startDate; }
	public void setStartDate(Date s0){ startDate = s0; }
	public Date getEndDate(){ return endDate; }
	public void setEndDate(Date e0){ endDate = e0; }
}
