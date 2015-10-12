package jp.ac.oit.igakilab.marsh.smanager.beans;

import java.util.Date;

import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.RecordList;

public class RecordListContainer {
	static Date getRLTS(RecordList recs){
		if( recs.getRecordCount() > 0 ){
			return recs.getRecord(0).getTimeStamp();
		}else{
			return null;
		}
	}

	public static void sortRecordLists(RecordList[] recs){
		int j;
		RecordList tmp;

		for(int i=1; i<recs.length; i++){
			j = i;
			while( (j > 0) &&
				((getRLTS(recs[j]) == null || getRLTS(recs[j-1]) == null) ||
				getRLTS(recs[j]).compareTo(getRLTS(recs[j-1])) > 0 )
			){
				tmp = recs[j];
				recs[j] = recs[j-1];
				recs[j-1] = tmp;
				j--;
			}
		}
	}

/* ------------------------------------- */

	private String label;
	private ActionRecordBean[] records;

	public RecordListContainer(String l0, ActionRecordBean[] r0){
		label = l0;
		records = r0;
	}

	public RecordListContainer(String l0, ActionRecord[] r0){
		label = l0;
		records = ActionRecordBean.toBeans(r0);
	}

	public RecordListContainer(RecordList r0){
		label = r0.getLabel();
		records = ActionRecordBean.toBeans(r0.toArray());
	}

	public String getLabel(){ return label; }
	public void setLabel(String l0){ label = l0; }
	public ActionRecordBean[] getRecords(){ return records; }
	public void setRecords(ActionRecordBean[] r0){ records = r0; }
}
