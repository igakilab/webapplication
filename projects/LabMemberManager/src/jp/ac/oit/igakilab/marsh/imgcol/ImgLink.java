package jp.ac.oit.igakilab.marsh.imgcol;

import java.util.Calendar;
import java.util.Date;

public class ImgLink {
	private String url;
	private Date timeStamp;

	public ImgLink(){
		init();
	}

	public ImgLink(String l0){
		init();
		setUrl(l0);
		setTimeStamp(Calendar.getInstance().getTime());
	}

	public ImgLink(String l0, Date d0){
		init();
		setUrl(l0);
		setTimeStamp(d0);
	}

	public void init(){
		url = "";
		timeStamp = null;
	}

	public String getUrl(){ return url; }
	public void setUrl(String l0){ url = l0; }
	public Date getTimeStamp(){ return timeStamp; }
	public void setTimeStamp(Date d0){ timeStamp = d0; }
}
