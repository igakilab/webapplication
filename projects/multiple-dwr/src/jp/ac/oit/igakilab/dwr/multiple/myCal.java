package jp.ac.oit.igakilab.dwr.multiple;

import java.util.Calendar;

public class myCal {
	/*インスタンス変数*/
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int second;

	public void setCalendar(Calendar cal){
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
	}

	public String toString(){
		return String.format("%4d/%02d/%02d %02d:%02d:%02d",
				year, month, day, hour, minute, second);
	}

}
