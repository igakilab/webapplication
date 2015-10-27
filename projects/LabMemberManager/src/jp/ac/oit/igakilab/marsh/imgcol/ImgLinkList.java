package jp.ac.oit.igakilab.marsh.imgcol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImgLinkList {
	private List<ImgLink> list;

	public ImgLinkList(){
		init();
	}

	public void init(){
		list = new ArrayList<ImgLink>();
	}

	public void add(ImgLink l0){
		int over = getIndexByUrl(l0.getUrl());
		if( over >= 0 && over < list.size() ){
			list.set(over, l0);
			return;
		}

		int idx = list.size();

		while( idx > 0 && list.get(idx - 1).getTimeStamp().compareTo(l0.getTimeStamp()) > 0 ){
			idx--;
		}

		if( idx >= list.size() ){
			list.add(l0);
		}else{
			list.add(idx, l0);
		}
	}

	public ImgLink get(int idx){
		if( idx < 0 || idx >= list.size()) return null;
		return list.get(idx);
	}

	public void remove(int idx){
		list.remove(idx);
	}

	public int size(){
		return list.size();
	}

	public ImgLink[] toArray(){
		return list.toArray(new ImgLink[list.size()]);
	}


	/*霑ｽ蜉�讖溯�ｽ*/
	public ImgLink[] getByDate(Date d_from, Date d_to){
		List<ImgLink> tmp = new ArrayList<ImgLink>();
		int idx = 0;

		while( list.get(idx).getTimeStamp().compareTo(d_from) < 0 && idx < list.size() ) idx++;
		while( list.get(idx).getTimeStamp().compareTo(d_to) <= 0 && idx < list.size() ){
			tmp.add(list.get(idx));
			idx++;
		}

		return tmp.toArray(new ImgLink[tmp.size()]);
	}

	public ImgLink[] getByDate(Date d_from){
		List<ImgLink> tmp = new ArrayList<ImgLink>();
		int idx = 0;

		while( list.get(idx).getTimeStamp().compareTo(d_from) < 0 && idx < list.size() ) idx++;
		while( idx < list.size() ){
			tmp.add(list.get(idx));
			idx++;
		}

		return tmp.toArray(new ImgLink[tmp.size()]);
	}

	public ImgLink[] getLatest(int cnt){
		List<ImgLink> tmp = new ArrayList<ImgLink>();
		int idx = list.size() - 1;

		while( idx >= 0 && idx > (list.size() - 1 - cnt) ){
			tmp.add(0, list.get(idx));
			idx--;
		}

		return tmp.toArray(new ImgLink[tmp.size()]);
	}

	public int getIndexByUrl(String u0){
		for(int i=0; i<list.size(); i++){
			if( list.get(i).getUrl().equals(u0) ){
				return i;
			}
		}
		return -1;
	}
}
