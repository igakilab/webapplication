package jp.ac.oit.igakilab.marsh.smanager.records;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.ac.oit.igakilab.marsh.util.DebugLog;
import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.CsvWriter;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

public class CsvRecordList extends RecordList {
	static String DF_PATTERN = "yyyy-MM-dd HH:mm:ss";
	static String FILE_HEADER = "RecList";
	static DebugLog logger = new DebugLog("CsvRecordList");

	static public boolean checkFile(File fp){
		FileReader reader = null;
		int tmp;
		boolean flag = true;

		try{
			reader = new FileReader(fp);

			for(int i=0; i<FILE_HEADER.length(); i++){
				if( (tmp = reader.read()) == -1 ) flag = false;
				if( (char)tmp != FILE_HEADER.charAt(i)) flag = false;
			}

			reader.close();
		}catch(IOException e0){
			flag = false;
		}

		return flag;
	}

	static public boolean checkFile(String fname){
		return checkFile(new File(fname));
	}

	static public List<String> toTokens(ActionRecord rec){
		List<String> tokens = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat(DF_PATTERN);
		tokens.add(df.format(rec.getTimeStamp()));
		tokens.add(rec.getId());
		tokens.add(Integer.toString(rec.getStateCode()));
		tokens.add(Integer.toString(rec.getTimeout()));
		return tokens;
	}

	static public ActionRecord toRecord(String[] strs)
	throws ParseException{
		DateFormat df = new SimpleDateFormat(DF_PATTERN);
		Date ts = df.parse(strs[0]);
		String id = strs[1];
		int code = Integer.parseInt(strs[2]);
		if( strs.length > 3 ){
			int timeout = Integer.parseInt(strs[3]);
			return new ActionRecord(ts, id, code, timeout);
		}else{
			return new ActionRecord(ts, id, code);
		}
	}

	static public void writeCsvHeader(CsvWriter writer)
	throws IOException {
		String[] labels = {"timestamp", "id", "code", "timeout"};
		List<String> tmp = new ArrayList<String>();
		tmp.add(FILE_HEADER);
		writer.writeValues(tmp);
		tmp.clear();
		for(int i=0; i<labels.length; i++){
			tmp.add(labels[i]);
		}
		writer.writeValues(tmp);
	}

	static public void addRecordToFile(String fname, ActionRecord rec)
	throws IOException{
		File fp = new File(fname);
		CsvWriter writer;
		List<String> tokens;

		if( fp.exists() ){
			if( checkFile(fp) ){
				writer = new CsvWriter(new FileWriter(fp, true));
			}else{
				logger.log("addRecordToFile: unspported file");
				return;
			}
		}else{
			writer = new CsvWriter(new FileWriter(fp, false));
			writeCsvHeader(writer);
		}

		tokens = toTokens(rec);
		writer.writeValues(tokens);
		writer.close();
	}


	//インスタン
	public CsvRecordList(){
		super();
	}

	public void importFile(String fname)
	throws IOException {
		List<String[]> buf;
		ActionRecord rec_tmp;
		File fp = new File(fname);

		if( !checkFile(fp) ){
			logger.log("importFile: file error");
			return;
		}

		buf = Csv.load(fp, new CsvConfig(), new StringArrayListHandler());

		if( !FILE_HEADER.equals(buf.get(0)[0]) ){
			logger.log("illigal format");
		}

		for(int i=2; i<buf.size(); i++){
			try{
				rec_tmp = toRecord(buf.get(i));
				addRecord(rec_tmp);
			}catch(ParseException e0){
				logger.log("[file:" + fname + ", rows:" + i + "] " + e0.getMessage());
			}
		}
		logger.log("file:" + fname + "is load complete");
	}

	public void exportFile(String fname)
	throws IOException{
		List<String> tmp;
		CsvWriter writer = new CsvWriter(new FileWriter(fname));

		writeCsvHeader(writer);
		for(int i=getRecordCount()-1; i>=0; i--){
			tmp = toTokens(getRecord(i));
			writer.writeValues(tmp);
		}

		writer.close();
	}

	public void addRecordList(RecordList recs){
		for(int i=recs.getRecordCount()-1; i>=0; i--){
			addRecord(recs.getRecord(i));
		}
	}
}