package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.io.IOException;

import jp.ac.oit.igakilab.marsh.dwr.beans.MemberIdBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberIdList;

public class IdlistConfig {
	MemberIdList list;

	public IdlistConfig(){
		init();
	}

	public void init(){
		list = new MemberIdList();
		try{
			list.importCsvFile("idlist_config.csv");
		}catch(IOException e){}
	}

	public MemberIdBean[] getIdList(){
		int len = list.getListLength();
		MemberIdBean[] bean = new MemberIdBean[len];

		for(int i=0; i<len; i++){
			bean[i] =
				new MemberIdBean(list.getNameByIndex(i), list.getIdByIndex(i));
		}

		return bean;
	}

	public MemberIdBean getId(int idx){
		MemberIdBean bean;
		bean = new MemberIdBean(list.getNameByIndex(idx), list.getIdByIndex(idx));
	}

	public void addId(String name, String id){
		list.addId(name, id);
	}

}
