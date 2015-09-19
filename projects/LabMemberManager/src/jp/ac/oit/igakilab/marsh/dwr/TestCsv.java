package jp.ac.oit.igakilab.marsh.dwr;

import java.io.IOException;

import jp.ac.oit.igakilab.marsh.smanager.CommonMemberSet;
import jp.ac.oit.igakilab.marsh.smanager.CommonStateSet;
import jp.ac.oit.igakilab.marsh.smanager.MemberIdList;
import jp.ac.oit.igakilab.marsh.smanager.StateList;

public class TestCsv {
	public void testOut(){
		StateList slist = CommonStateSet.LIST;
		MemberIdList mlist = CommonMemberSet.LIST;

		try{
			slist.exportCsvFile("export_state.csv");
			mlist.exportCsvFile("export_memid.csv");
		}catch(IOException e){}
	}

	public String[] testInState(){
		StateList slist = new StateList();

		try{
			slist.importCsvFile("export_state.csv");
		}catch(IOException e){}

		return slist.getStateListString();
	}


	public String[] testInMemid(){
		MemberIdList mlist = new MemberIdList();

		try{
			mlist.importCsvFile("export_memid.csv");
		}catch(IOException e){}

		return mlist.getIdListString();
	}
}
