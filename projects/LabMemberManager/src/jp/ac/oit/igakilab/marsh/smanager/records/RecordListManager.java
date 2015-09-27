package jp.ac.oit.igakilab.marsh.smanager.records;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class RecordListManager {
//スタティックメソッド
	static public RecordList loadCsvRecordList(String file_name){
		CsvRecordList rec_list = new CsvRecordList();
		try {
			rec_list.importFile(file_name);
		}catch(IOException e0){
			DebugLog.out("RecordListManager(01): 入出力エラーが発生しました");
			return null;
		}
		return rec_list;
	}

	static public void saveCsvRecordList(String file_name, RecordList recs){
		CsvRecordList c_rec = new CsvRecordList();
		c_rec.addRecordList(recs);
		try {
			c_rec.exportFile(file_name);
		}catch(IOException e0){
			DebugLog.out("RecordListManager(02): 入出力エラーが発生しました");
		}
	}

	static public String getDateFilename(Date date){
		String head = "records_";
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return head + df.format(date) + ".csv";
	}

	static public void outputSingleRecord(String file_name, ActionRecord rec){
		try {
			CsvRecordList.addRecordToFile(file_name, rec);
		}catch(IOException e0){
			DebugLog.out("RecordListManager(03): 入出力エラーが発生しました");
		}
	}



//インスタンス
	//フィールド
	RecordList buffer;

	//メソッド
	public void init(){
		buffer = new RecordList();
	}

	public void addReocrd(ActionRecord recs){
		buffer.addRecord(recs);
		outputSingleRecord(
			getDateFilename(recs.getTimeStamp()),
			recs
		);
	}

	public void getBufferRecordList(){
		return buffer;
	}

	public void getAllRecordList(){
		return
	}




}
