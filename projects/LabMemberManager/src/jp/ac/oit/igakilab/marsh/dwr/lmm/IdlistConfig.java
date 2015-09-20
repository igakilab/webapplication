package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.io.IOException;

import jp.ac.oit.igakilab.marsh.dwr.beans.MemberIdBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberIdList;
import jp.ac.oit.igakilab.marsh.smanager.MemberStateManager;

public class IdlistConfig {
	public static String CONF_IDLIST_FILE = MemberStateManager.CONF_IDLIST_FILE;

	MemberIdList list;

	public IdlistConfig(){
		init();
	}

	public void init(){
		list = new MemberIdList();
		try{
			list.importCsvFile(CONF_IDLIST_FILE);
		}catch(IOException e1){}
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
		return bean;
	}

	public void addId(String name, String id){
		list.addId(name, id);
	}

	public void setId(int idx, String name, String id){
		list.setIdByIndex(idx, name, id);
	}

	public void deleteId(int idx){
		list.deleteIdByIndex(idx);
	}

	public void export(){
		try{
			list.exportCsvFile(CONF_IDLIST_FILE);
		}catch(IOException e0){}
	}
}
