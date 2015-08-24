package jp.ac.oit.igakilab.marsh.smanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StateManager extends StateList{
	static final long TIMEOUT = 300 * 1000;
	static final String LOG_FILE = "logs.txt";

	public StateManager(){
		writeLog("<bigin> new instance created!");
	}


	public void updateStateList(){
		int length;
		long t_now, t_update;

		writeLog("List Update");

		length = getMemberNum();
		for(int i=length - 1; i>=0; i--){
			t_update = getMemberInfo(i).getUpdateDate().getTime().getTime();
			t_now = Calendar.getInstance().getTime().getTime();

			if( (t_now - t_update) > TIMEOUT ){
				writeLog(String.format("username:%s is timeout", getMemberInfo(i).getName()));
				deleteMember(i);
			}
		}
	}

	public void addMember(String name, int state){
		writeLog(String.format("username:%s is logined", name));
		super.addMember(name, state);
	}

	public void writeLog(String message){
		PrintWriter pw = null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String logline;

		logline = "[" + df.format(Calendar.getInstance().getTime()) + "] ";
		logline += message;

		try {
			pw = new PrintWriter(new BufferedWriter(
					new FileWriter(LOG_FILE, true)
			));

			pw.println(logline);
		}catch (Exception e){
			System.err.println("LOG WRITE ERROR");
		}finally{
			pw.close();
		}
	}
}
