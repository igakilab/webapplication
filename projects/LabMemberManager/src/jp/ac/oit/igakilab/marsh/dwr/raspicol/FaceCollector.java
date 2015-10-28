package jp.ac.oit.igakilab.marsh.dwr.raspicol;

import jp.ac.oit.igakilab.marsh.imgcol.FixedImgLinkList;
import jp.ac.oit.igakilab.marsh.imgcol.ImgLink;
import jp.ac.oit.igakilab.marsh.imgcol.ImgLinkBean;

public class FaceCollector {
	static final int FIXED_LEN = 100;

	FixedImgLinkList list;

	public FaceCollector(){
		init();
	}

	public void init(){
		list = new FixedImgLinkList(FIXED_LEN);
	}

	public void addImg(String client_addr, String img_name){
		String url = "http://" + client_addr + "/images/" + img_name;
		list.add(new ImgLink(url));
	}

	public ImgLinkBean[] getImg(){
		return ImgLinkBean.toBeans(list.toArray());
	}

	public ImgLinkBean[] getImgLatest(int cnt){
		return ImgLinkBean.toBeans(list.getLatest(cnt));
	}
}
