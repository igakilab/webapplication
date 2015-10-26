package jp.ac.oit.igakilab.marsh.dwr;

import jp.ac.oit.igakilab.marsh.smanager.members.Member;
import jp.ac.oit.igakilab.marsh.smanager.members.XmlMemberList;

public class TestMemberList {
	XmlMemberList mlist;

	public TestMemberList(){
		init();
	}

	public void init(){
		mlist = new XmlMemberList();
	}

	public void fileLoad(String fn0){
		mlist.loadXmlFile(fn0);
	}

	public void fileSave(String fn0){
		mlist.saveXmlFile(fn0);
	}

	public void addMember(String name){
		Member mem = new Member(name);
		mlist.addMember(mem);
	}

	public void setPassword(String name, String passwd){
		Member mem = mlist.getMember(name);
		if( mem == null ) return;
		mem.setPassword(passwd);
	}

	public void addIdConvert(String name, String id){
		Member mem = mlist.getMember(name);
		if( mem == null ) return;
		mem.addConvertId(id);
	}

	public Member[] getMemberList(){
		return mlist.toArray();
	}
}
