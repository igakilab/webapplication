package jp.ac.oit.igakilab.marsh.dwr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.beans.ActionRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.CsvRecordList;
import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class TestCsv {
	CsvRecordList crl;

	public TestCsv(){
		init();
	}

	public void init(){
		crl = new CsvRecordList();
	}

	public void addRecord(String id, int code){
		crl.addRecord(new ActionRecord(id, code));
	}

	public ActionRecordBean[] getRecordList(){
		List<ActionRecordBean> beans = new ArrayList<ActionRecordBean>();
		int len = crl.getRecordCount();
		for(int i=0; i<len; i++){
			beans.add(new ActionRecordBean(crl.getRecord(i)));
		}
		return beans.toArray(new ActionRecordBean[0]);
	}

	public void exportList(){
		exportListByName("test_output.csv");
	}

	public void exportListByName(String file_name){
		try{
			crl.exportFile(file_name);
		}catch(IOException e0){}
	}

	public String importList(){
		return importListByName("test_output.csv");
	}

	public String importListByName(String file_name){
		if( new File(file_name).exists() ){
			try{
				crl.importFile(file_name);
			}catch(IOException e0){}
		}else{
			return "file not found";
		}
		return "complete";
	}

	public ActionRecordBean[] loadFile(String file_name){
		CsvRecordList list = new CsvRecordList();
		ActionRecordBean[] beans;
		int length;
		try{
			list.importFile(file_name);
		}catch(IOException e0){DebugLog.out("error392");}

		length = list.getRecordCount();
		beans = new ActionRecordBean[length];
		for(int i=0; i<list.getRecordCount(); i++){
			beans[i] = new ActionRecordBean(list.getRecord(i));
		}

		return beans;
	}

	public String checkFile(String file_name){
		if( CsvRecordList.checkFile(file_name) ){
			return "supported file";
		}else{
			return "unsupported file";
		}
	}
}
;