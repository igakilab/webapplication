package jp.ac.oit.igakilab.marsh.util;

public class DebugLog {
	private static LogRecorder INSTANCE = null;
	public static final String FILE_NAME = "debug_msg.txt";

	static void initInstance(){
		if( INSTANCE == null ){
			INSTANCE = new LogRecorder(FILE_NAME, true);
		}
	}

	public static void out(String fnc, String msg){
		initInstance();
		INSTANCE.addSingleLog(fnc + ": " + msg, true);
	}

	public static void out(String msg){
		initInstance();
		INSTANCE.addSingleLog(msg, true);
	}
}
