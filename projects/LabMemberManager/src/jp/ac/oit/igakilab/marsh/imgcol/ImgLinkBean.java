package jp.ac.oit.igakilab.marsh.imgcol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ImgLinkBean {
	public static ImgLinkBean[] toBeans(ImgLink[] links){
		ImgLinkBean[] beans = new ImgLinkBean[links.length];
		for(int i=0; i<links.length; i++){
			beans[i] = new ImgLinkBean(links[i]);
		}
		return beans;
	}



	private String url;
	private String timeStamp;

	public ImgLinkBean(){
		init();
	}

	public ImgLinkBean(ImgLink i0){
		init();
		toBean(i0);
	}

	public void init(){
		url = "";
		timeStamp = "";
	}

	public void toBean(ImgLink l0){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setUrl(l0.getUrl());
		setTimeStamp(df.format(l0.getTimeStamp()));
	}

	public String getUrl(){ return url; }
	public void setUrl(String l0){ url = l0; }
	public String getTimeStamp(){ return timeStamp; }
	public void setTimeStamp(String t0){ timeStamp = t0; }
}
