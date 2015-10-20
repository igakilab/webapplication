package jp.ac.oit.igakilab.marsh.dwr;

import java.io.IOException;
import java.util.Date;

import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;
import jp.ac.oit.igakilab.marsh.smanager.MemberState;
import jp.ac.oit.igakilab.marsh.smanager.beans.ActionRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.HistoryRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.HistoryStatisticsBean;
import jp.ac.oit.igakilab.marsh.smanager.beans.MemberStateBean;
import jp.ac.oit.igakilab.marsh.smanager.history.HistoryStatistics;
import jp.ac.oit.igakilab.marsh.smanager.records.CsvRecordList;
import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class TestStatistics {
	static String SAMPLE_FILE_PATH = "test/sample.csv";
	static DebugLog LOGGER = new DebugLog("TestStatistics");

	CsvRecordList recs;

	public TestStatistics(){
		init();
	}

	public void init(){
		recs = new CsvRecordList();
		if( CsvRecordList.checkFile(SAMPLE_FILE_PATH) ){
			try{
				recs.importFile(SAMPLE_FILE_PATH);
			}catch(IOException e0){
				LOGGER.log("init", e0.getMessage());
			}
		}else{
			LOGGER.log("init", "SAMPLE_FILE_PATHが不正なファイルです");
		}
	}

	public ActionRecordBean[] getRecordList(){
		return ActionRecordBean.toBeans(recs.toArray());
	}

	MemberState getMemberStateInstance(String id){
		MemberState ms = new MemberState(id);
		ms.updateActionRecord(recs);
		return ms;
	}

	public MemberStateBean getMemberState(String name){
		MemberState ms = getMemberStateInstance(name);
		return new MemberStateBean(ms, CommonStateSet.LIST);
	}

	public HistoryRecordBean[] getHistoryList(String name){
		MemberState ms = getMemberStateInstance(name);
		return HistoryRecordBean.toBeans(ms.getHistoryList());
	}

	public HistoryStatisticsBean[] getStatistics(String name){
		MemberState ms = getMemberStateInstance(name);
		HistoryStatistics stat = new HistoryStatistics();
		Date end_date = recs.getRecord(0).getTimeStamp();
		stat.analyze(ms.getHistoryList(), end_date);
		return HistoryStatisticsBean.toBeans(stat);
	}
}