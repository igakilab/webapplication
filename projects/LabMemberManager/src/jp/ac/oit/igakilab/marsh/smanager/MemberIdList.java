package jp.ac.oit.igakilab.marsh.smanager;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.util.DebugLog;
import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;


public class MemberIdList {
	static boolean ID_OVERLAP = false;

	List<String> identifers;
	List<String> names;


	public MemberIdList(){
		identifers = new ArrayList<String>();
		names = new ArrayList<String>();
	}


	public void addId(String name, String id){
		if( isIdRegisted(id) && isNameRegisted(name) ){
			DebugLog.logm("MemberIdList", DebugLog.LS_WARN, "addId", "データが重複しています");
			DebugLog.logm("MemberIdList", "name = " + name + " id = " + id);
			return;
		}
		if( !isIdRegisted(id) || ID_OVERLAP ){
			identifers.add(id);
			names.add(name);
		}
	}


	public String getNameById(String id){
		for(int i=0; i<identifers.size(); i++){
			if( identifers.get(i).equals(id) ){
				return names.get(i);
			}
		}
		return null;
	}


	public String[] getNameListById(String id){
		List<String> tmp = new ArrayList<String>();
		for(int i=0; i<identifers.size(); i++){
			if( identifers.get(i).equals(id) ){
				tmp.add(names.get(i));
			}
		}
		return tmp.toArray(new String[0]);
	}


	public String[] getIdByName(String name){
		List<String> tmp = new ArrayList<String>();
		for(int i=0; i<names.size(); i++){
			if( names.get(i).equals(name) ){
				tmp.add(identifers.get(i));
			}
		}
		return tmp.toArray(new String[0]);
	}


	public String[] getRegistedIdList(){
		List<String> id_list = new ArrayList<String>();

		for(int i=0; i<identifers.size(); i++){
			if( !id_list.contains(identifers.get(i)) ){
				id_list.add(identifers.get(i));
			}
		}

		return id_list.toArray(new String[0]);
	}


	public String[] getRegistedNameList(){
		List<String> name_list = new ArrayList<String>();

		for(int i=0; i<names.size(); i++){
			if( !name_list.contains(names.get(i)) ){
				name_list.add(names.get(i));
			}
		}

		return name_list.toArray(new String[0]);
	}


	public String getNameByIndex(int idx){
		if( idx >= 0 && idx < names.size() ){
			return names.get(idx);
		}else{
			return null;
		}
	}

	public String getIdByIndex(int idx){
		if( idx >= 0 && idx < identifers.size() ){
			return identifers.get(idx);
		}else{
			return null;
		}
	}

	public void setIdByIndex(int idx, String name, String id){
		if( idx >= 0 && idx < identifers.size() ){
			names.set(idx , name);
			identifers.set(idx, id);
		}
	}

	public void deleteIdByIndex(int idx){
		if( idx >= 0 && idx < identifers.size() ){
			names.remove(idx);
			identifers.remove(idx);
		}
	}

	public int getListLength(){
		return names.size();
	}

	public String[] getIdListString(){
		List<String> tmp = new ArrayList<String>();

		for(int i=0; i<names.size(); i++){
			tmp.add("[" + names.get(i) + ", " + identifers.get(i) + "]");
		}

		return tmp.toArray(new String[0]);
	}

	public boolean isIdRegisted(String id){
		return identifers.contains(id);
	}

	public boolean isNameRegisted(String name){
		return names.contains(name);
	}


	public void swapRow(int i1, int i2){
		String tmp;
		tmp = identifers.get(i1);
		identifers.set(i1, identifers.get(i2));
		identifers.set(i2, tmp);
		tmp = names.get(i1);
		names.set(i1, names.get(i2));
		names.set(i2, tmp);
	}

	public void sortRow(){
		Collator col = Collator.getInstance();
		for(int i=0; i<names.size(); i++){
			for(int j=i; j>0; j--){
				if( col.compare(names.get(j), names.get(j-1)) < 0 ){
					swapRow(j, j-1);
				}
			}
		}
	}


	/*ファイル操作*/
	public void importCsvFile(String file_name)
	throws IOException {
		List<String[]> buffer;
		String[] row;

		buffer = Csv.load(new File(file_name), new CsvConfig(), new StringArrayListHandler());

		if( buffer.size() >= 2 ){
			if( buffer.get(0)[0].equals("id_list") ){

				for(int i=2; i<buffer.size(); i++){
					row = buffer.get(i);
					if( row.length >= 2 ){
						addId(row[0], row[1]);
					}
				}
			}
		}
	}


	public void exportCsvFile(String file_name)
	throws IOException {
		List<String[]> buffer = new ArrayList<String[]>();
		List<String> tmp = new ArrayList<String>();
		String[] header = {"id_list"};
		String[] labels = {"#name", "#id"};

		buffer.add(header);
		buffer.add(labels);

		for(int i=0; i<names.size(); i++){
			tmp.clear();
			tmp.add(names.get(i));
			tmp.add(identifers.get(i));
			buffer.add(tmp.toArray(new String[0]));
		}

		Csv.save(buffer, new File(file_name), new CsvConfig(), new StringArrayListHandler());
	}
}


/*
	addId();

*/