package jp.ac.oit.igakilab.marsh.dwr;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class LogTest {
	DebugLog instance;

	public LogTest(){
		instance = new DebugLog("INSTANCE");
	}

	public void doTests(){
		instance.log(DebugLog.LS_INFO, "FUNCTION", "MESSAGE");
		instance.log("FUNCTION", "MESSAGE");
		instance.log(DebugLog.LS_WARN, "MESSAGE");
		instance.log("MESSAGE ONLY");

		DebugLog.logm("TESTMOD1", DebugLog.LS_ERROR, "FUNCTION", "MESSAGE");
		DebugLog.logm("TESTMOD2", DebugLog.LS_EXCEPTION, "MESSAGE");
		DebugLog.logm("TESTMOD3", "FUNCTION", "MESSAGE");
		DebugLog.logm("TESTMOD4", "MESSAGE ONLY");

		DebugLog.logs(DebugLog.LS_FATAL, "FUNCTION", "MESSAGE");
		DebugLog.logs(999, "TYPECODE UNDEFINED");
		DebugLog.logs("FUNCTION", "MESSAGE");
		DebugLog.logs("MESSAGE ONLY");
	}

}
