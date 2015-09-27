package jp.ac.oit.igakilab.marsh.smanager.records;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class RecordListManager {
//スタティック変数
	public static String CSV_DIR = "records/";

//スタティックメソッド
	static public RecordList loadCsvRecordList(String file_name){
		CsvRecordList rec_list = new CsvRecordList();
		try {
			rec_list.importFile(CSV_DIR + file_name);
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
			c_rec.exportFile(CSV_DIR + file_name);
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
			CsvRecordList.addRecordToFile(CSV_DIR + file_name, rec);
		}catch(IOException e0){
			DebugLog.out("RecordListManager(03): 入出力エラーが発生しました");
		}
	}

	static public String[] getRecordFileList(){
		String[] list = new File(CSV_DIR).list();
		List<String> files = new ArrayList<String>();
		File tmp;
		for(int i=0; i<list.length; i++){
			tmp = new File(list[i]);
			if( tmp.isFile() && CsvRecordList.checkFile(tmp) ){
				files.add(list[i]);
			}
		}
		return files.toArray(new String[0]);
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

	public RecordList getBufferRecordList(){
		return buffer;
	}

	public RecordList getAllRecordList(){
		CsvRecordList all_list = new CsvRecordList();
		String[] file_list = getRecordFileList();
		for(int i=0; i<file_list.length; i++){
			try {
				all_list.importFile(CSV_DIR + file_list[i]);
			}catch(IOException e0){
				DebugLog.out("getAllRecordList: IOException" + e0.getMessage());
			}
		}
		return all_list;
	}

	public void searchRecordList(RecordSearcher searcher){
		String[] file_list = getRecordFileList();
		CsvRecordList tmp = new CsvRecordList();
		for(int i=0; i<file_list.length; i++){
			tmp.clear();
			try {
				tmp.importFile(CSV_DIR + file_list[i]);
			}catch(IOException e0){
				DebugLog.out("searchRecordList: IOException" + e0.getMessage());
			}
			searcher.excute(tmp);
		}
	}
}
