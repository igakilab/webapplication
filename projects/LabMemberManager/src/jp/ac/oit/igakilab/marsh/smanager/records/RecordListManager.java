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
	public static String CSV_DIR = "LabMemberManager/records/";
	public static int BUFFER_LENGTH = 200;

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
			File dir = new File(CSV_DIR);
			if( !dir.exists()) dir.mkdirs();
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
			File dir = new File(CSV_DIR);
			if( !dir.exists() ) dir.mkdirs();
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
			DebugLog.logm("RecordListManager", "getRecordFileList", "list["+i+"]="+list[i]); //デバッグ用
			tmp = new File(CSV_DIR + list[i]);
			//デバッグ用↓
			DebugLog.logm("RecordListManager", "isFile=" + tmp.isFile() + " csvCheck=" + CsvRecordList.checkFile(tmp));
			if( tmp.isFile() && CsvRecordList.checkFile(tmp) ){
				files.add(list[i]);
			}
		}
		return files.toArray(new String[0]);
	}


//インスタンス
	//フィールド
	FixedRecordList buffer;

	//コンストラクタ
	public RecordListManager(){
		init();
	}

	//メソッド
	public void init(){
		buffer = new FixedRecordList(BUFFER_LENGTH);
	}

	public void addRecord(ActionRecord recs){
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
		DebugLog.logm("RecordListManager", DebugLog.LS_INFO, "record length = " + file_list.length);
		for(int i=0; i<file_list.length; i++){
			try {
				all_list.importFile(CSV_DIR + file_list[i]);
			}catch(IOException e0){
				DebugLog.out("getAllRecordList: IOException" + e0.getMessage());
			}
		}
		return all_list;
	}

	public RecordList[] getAllRecordLists(){
		CsvRecordList tmp;
		String[] file_list = getRecordFileList();
		RecordList[] lists = new RecordList[file_list.length];
		for(int i=0; i<file_list.length; i++){
			tmp = new CsvRecordList();
			tmp.setLabel(file_list[i]);
			try{
				tmp.importFile(CSV_DIR + file_list[i]);
			}catch(IOException e0){
				DebugLog.logm("RecordListManager", "getAllRecordLists", e0.getMessage());
			}
			lists[i] = tmp;
		}
		return lists;
	}

	public void loadAllRecordsToBuffer(){
		String[] file_list = getRecordFileList();
		CsvRecordList tmp = new CsvRecordList();
		for(int i=0; i<file_list.length; i++){
			try {
				tmp.importFile(CSV_DIR + file_list[i]);
			}catch(IOException e0){
				DebugLog.logm("RecordListManager", "loadAllRecords:", e0.getMessage());
			}
		}
		buffer.init();
		buffer.addRecordList(tmp);
	}

	public void searchRecordList(RecordSearcher searcher){
		String[] file_list = getRecordFileList();
		CsvRecordList tmp = new CsvRecordList();
		searcher.init();
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

	public void searchBufferRecordList(RecordSearcher searcher){
		searcher.init();
		searcher.excute(buffer);
	}
}
