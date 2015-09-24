package jp.ac.oit.igakilab.marsh.smanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

public class StateList {
	/*定数*/
	public static final int STATE_NOTFOUND = 8001;

	/*インスタンス変数*/
	List<StateInfo> list;


	/*コンストラクタ*/
	public StateList(){
		list = new ArrayList<StateInfo>();
	}


	/*メソッド*/
	public void addState(StateInfo si){
		for(int i=0; i<list.size(); i++){
			if( si.getName().equals(list.get(i).getName()) ){
				return;
			}
		}

		list.add(si);
	}

	public int searchStateInfo(int code){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).getCode() == code){
				return i;
			}
		}

		return -1;
	}

	public String getStateName(int code){
		int idx = searchStateInfo(code);

		if( idx >= 0 ){
			return list.get(idx).getName();
		}else{
			return "UNDEFINED";
		}
	}

	public int getStateTimeout(int code){
		int idx = searchStateInfo(code);

		if( idx >= 0){
			return list.get(idx).getTimeout();
		}else{
			return -1;
		}
	}

	public boolean isStateRegisted(int code){
		return searchStateInfo(code) >= 0;
	}

	public boolean checkStateTimeout(int code, Date ts, Date tn){
		long t_out = getStateTimeout(code) * 1000;
		long t_stp = ts.getTime();
		long t_now = tn.getTime();

		return (t_out > 0) && (t_now - t_stp > t_out);
	}


	/*デバッグ等用メソッド*/
	public void clearStateList(){
		list.clear();
	}

	public int getStateListLength(){
		return list.size();
	}

	public String[] getStateListString(){
		int len = list.size();
		String slist[] = new String[len];

		for(int i=0; i<len; i++){
			slist[i] = list.get(i).toString();
		}

		return slist;
	}


	/*ファイル操作*/
	public void importCsvFile(String file_name)
	throws IOException {
		List<String[]> buffer;
		String[] row;
		int code, timeout;

		buffer = Csv.load(new File(file_name),  new CsvConfig(), new StringArrayListHandler());

		if( buffer.size() < 2 ) return;
		if( !buffer.get(0)[0].equals("state_list") ) return;

		for(int i=2; i<buffer.size(); i++){
			row = buffer.get(i);
			if( row.length >= 3 ){
				try{
					code = Integer.parseInt(row[0]);
					timeout = Integer.parseInt(row[2]);
					addState(new StateInfo(code, row[1], timeout));
				}catch( NumberFormatException e){}
			}
		}
	}

	public void exportCsvFile(String file_name)
	throws IOException {
		List<String[]> buffer = new ArrayList<String[]>();
		List<String> tmp = new ArrayList<String>();
		StateInfo s_tmp;
		String[] header = {"state_list"};
		String[] labels = {"#code", "#name", "#timeout"};

		buffer.add(header);
		buffer.add(labels);

		for(int i=0; i<list.size(); i++){
			s_tmp = list.get(i);
			tmp.clear();
			tmp.add(Integer.toString(s_tmp.getCode()));
			tmp.add(s_tmp.getName());
			tmp.add(Integer.toString(s_tmp.getTimeout()));
			buffer.add(tmp.toArray(new String[0]));
		}

		Csv.save(buffer, new File(file_name), new CsvConfig(), new StringArrayListHandler());
	}
}
