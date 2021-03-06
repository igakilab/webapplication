package jp.ac.oit.igakilab.dwr.multiple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class MultiplePrinter {
	static String LOG_FILE = "../webapps/ROOT/logs.txt";

    public String helloWorld(String name){
    	return name + ":HelloWorld";

    }

    public String signin(String name){
    	PrintWriter pw = null;
    	myCal cal = new myCal();

    	try {
    		pw = new PrintWriter(
    			new BufferedWriter(
    				new FileWriter(LOG_FILE, true)
    			)
    		);

    		cal.setCalendar(Calendar.getInstance());
    		pw.println("[" + cal.toString() + "] " + "signin : " + name + "<br>");
    	}catch (Exception e){
    		return "SIGNIN ERROR > " + e.toString();

    	}finally {
    		pw.close();
    	}

    	return "signined <" + name + ", [" + cal.toString() + "]>";

    }

    public String log_clear(){
    	PrintWriter pw = null;
    	myCal cal = new myCal();

    	try {
    		pw = new PrintWriter(
    			new BufferedWriter(
    				new FileWriter(LOG_FILE, false)
    			)
    		);

    		cal.setCalendar(Calendar.getInstance());
    		pw.println("Log file was cleaned in [" + cal.toString() + "]<br>");
    		pw.println("----------<br>");
    	}catch(Exception e){
    		System.err.println("LOG CLEAR ERROR");

    	}finally{
    		pw.close();
    	}

    	return "cleaned! (" + cal.toString() + ")";
    }

    /*
    String calToStr(Calendar cal){
    	String str = "";

    	str += cal.get(Calendar.YEAR) + "/";
    	str += cal.get(Calendar.MONTH) + "/";
    	str += cal.get(Calendar.DATE) + " ";
    	str += cal.get(Calendar.HOUR_OF_DAY) + ":";
    	str += cal.get(Calendar.MINUTE) + ":";
    	str += cal.get(Calendar.SECOND);

    	return str;
    }
    */

}