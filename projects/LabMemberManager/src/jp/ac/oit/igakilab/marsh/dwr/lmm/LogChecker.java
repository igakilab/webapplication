package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class LogChecker {
	static String LOG_DIR = "logs/";

	public String[] getLogfileList(){
		FileSearch search = new FileSearch();
		File[] files = search.listFiles(LOG_DIR, "*.txt");
		String[] list = new String[files.length];
		for(int i=0; i<files.length; i++){
			list[i] = files[i].getName();
		}
		return list;
	}

	public String[] getLog(String file_name){
		List<String> logs = new ArrayList<String>();
		String tmp;
		BufferedReader reader;

		try {
			 reader = new BufferedReader(new FileReader(LOG_DIR + file_name));

			while((tmp = reader.readLine()) != null){
				logs.add(tmp);
			}

			reader.close();
		}catch(IOException e0){
			DebugLog.logm("LogChecker", DebugLog.LS_ERROR, e0.getMessage());
		}

		return logs.toArray(new String[logs.size()]);
	}
}
