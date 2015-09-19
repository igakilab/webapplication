package jp.ac.oit.igakilab.marsh.dwr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

public class TestCsv {
	public void testOut(){
		List<String[]> lst = new ArrayList<String[]>();
		String[] a1 = {"りょうくん", "ryokn"}, a2 = {"しにゃ", "shiya"},
				a3 = {"きたば", "kitab"};

		lst.add(a1);
		lst.add(a2);
		lst.add(a3);

		try{
			Csv.save(lst, new File("test_export.csv"), new CsvConfig(), new StringArrayListHandler());
		}catch (IOException e){

		}
	}
}
