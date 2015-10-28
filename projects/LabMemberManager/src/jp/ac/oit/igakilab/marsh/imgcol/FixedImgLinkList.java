package jp.ac.oit.igakilab.marsh.imgcol;

public class FixedImgLinkList extends ImgLinkList{
	int fixed_length;

	public FixedImgLinkList(){
		init();
	}

	public FixedImgLinkList(int l0){
		init();
		fixed_length = l0;
	}

	public void init(){
		super.init();
		fixed_length = 0;
	}

	public void add(ImgLink l0){
		super.add(l0);
		normalize();
	}

	public void setFixedLength(int l0){
		fixed_length = l0;
		normalize();
	}

	public void normalize(){
		while( size() > fixed_length ){
			remove(0);
		}
	}
}
