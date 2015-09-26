package jp.ac.oit.igakilab.marsh.smanager.records;

import java.io.File;
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
	static DateFormat DF= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String header = "RecList";

	public CsvRecordList(){
		super();
	}

	public void importFile(String fname)
	throws IOException {
		List<String[]> buf;
		ActionRecord rec_tmp;
		buf = Csv.load(new File(fname), new CsvConfig(), new StringArrayListHandler());

		if( !header.equals(buf.get(0)[0]) ){
			DebugLog.out("illigal format");
		}

		for(int i=2; i<buf.size(); i++){
			try{
				rec_tmp = toRecord(buf.get(i));
			}catch(ParseException e0){
				DebugLog.out("PARSEERROR: " + e0.getMessage());
				break;
			}
			addRecord(rec_tmp);
		}
	}

	public void exportFile(String fname)
	throws IOException{
		List<String> tmp;
		String[] labels = {"timestamp", "id", "code"};
		CsvWriter writer = new CsvWriter(new FileWriter(fname));

		tmp = new ArrayList<String>();
		tmp.add(header);
		writer.writeValues(tmp);
		tmp.clear();
		for(int i=0; i<labels.length; i++){
			tmp.add(labels[i]);
		}
		writer.writeValues(tmp);

		for(int i=getRecordCount()-1; i>=0; i--){
			tmp = toTokens(getRecord(i));
			writer.writeValues(tmp);
		}

		writer.close();
	}

	public List<String> toTokens(ActionRecord rec){
		List<String> tokens = new ArrayList<String>();
		tokens.add(DF.format(rec.getTimeStamp()));
		tokens.add(rec.getId());
		tokens.add(Integer.toString(rec.getStateCode()));
		return tokens;
	}

	public ActionRecord toRecord(String[] strs)
	throws ParseException{
		Date ts = DF.parse(strs[0]);
		String id = strs[1];
		int code = Integer.parseInt(strs[2]);
		return new ActionRecord(ts, id, code);
	}

	public void addRecordList(RecordList recs){
		for(int i=recs.getRecordCount()-1; i>=0; i--){
			addRecord(recs.getRecord(i));
		}
	}
}
