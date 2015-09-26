package jp.ac.oit.igakilab.marsh.dwr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.beans.ActionRecordBean;
import jp.ac.oit.igakilab.marsh.smanager.records.ActionRecord;
import jp.ac.oit.igakilab.marsh.smanager.records.CsvRecordList;

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
		try{
			crl.exportFile("test_output.csv");
		}catch(IOException e0){}
	}

	public void importList(){
		if( new File("test_output.csv").exists() ){
			try{
				crl.importFile("test_output.csv");
			}catch(IOException e0){}
		}
	}
}
