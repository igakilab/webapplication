package jp.ac.oit.igakilab.marsh.dwr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jp.ac.oit.igakilab.marsh.smanager.records.CsvRecordList;
import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class TestStatistics {
	static String SAMPLE_FILE_PATH = "test/sample.csv";
	static DateFormat DPARSER = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
	static DebugLog LOGGER = new DebugLog("TestStatistics");

	CsvRecordList recs;

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



}