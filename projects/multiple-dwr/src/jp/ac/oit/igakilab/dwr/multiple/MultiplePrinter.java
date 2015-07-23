package jp.ac.oit.igakilab.dwr.multiple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class MultiplePrinter {
	static String LOG_DSTDIR = "../webapps/ROOT/";

    public String helloWorld(String name){
    	return name + ":HelloWorld";

    }

    public String signin(String name){
    	PrintWriter pw = null;
    	myCal cal = new myCal();


    	try {
    		pw = new PrintWriter(
    			new BufferedWriter(
    				new FileWriter(LOG_DSTDIR + "logs.txt", true)
    			)
    		);

    		cal.setCalendar(Calendar.getInstance());
    		pw.println("[" + cal.toString() + "] " + "signin : " + name + "<br>");
    	}catch (Exception e){

    	}finally {
    		pw.close();
    	}

    	return "signined <" + name + ">";

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