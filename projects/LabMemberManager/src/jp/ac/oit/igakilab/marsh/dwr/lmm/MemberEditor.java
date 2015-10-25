package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.io.IOException;

import jp.ac.oit.igakilab.marsh.smanager.MemberIdList;
import jp.ac.oit.igakilab.marsh.smanager.MemberStateManager;
import jp.ac.oit.igakilab.marsh.smanager.members.Member;
import jp.ac.oit.igakilab.marsh.smanager.members.XmlMemberList;

public class MemberEditor {
	static String CONFIG_FILE_NAME = MemberStateManager.CONF_MEMBER_FILE;
	static String ROOT_PASSWORD = "marshmallow";

	XmlMemberList member_list;

	public MemberEditor(){
		init();
	}

	public void init(){
		member_list = new XmlMemberList();
		member_list.loadXmlFile(CONFIG_FILE_NAME);
	}

	public void save(){
		member_list.saveXmlFile(CONFIG_FILE_NAME);
	}

	public String add_member(String passwd, String name){
		if( ROOT_PASSWORD.equals(passwd) ){
			member_list.addMember(new Member(name));
			save();
			return "Member(" + name + ") Registed";
		}else{
			return "password error";
		}
	}

	public String delete_member(String passwd, String name){
		if( ROOT_PASSWORD.equals(passwd) ){
			if( member_list.deleteMember(name) ){
				save();
				return "delete complete";
			}else{
				return "member not found";
			}
		}else{
			return "password error";
		}
	}

	public String set_password(String name, String passwd, String new_password){
		Member mem = member_list.getMember(name);
		if( mem == null ) return "member not found";

		if( mem.getPassword().equals(passwd) ){
			mem.setPassword(passwd);
			save();
			return "set password complete";
		}else{
			return "password error";
		}
	}

	public String set_addconvid(String name, String passwd, String id){
		Member mem = member_list.getMember(name);
		if( mem == null ) return "member not found";

		if( mem.getPassword().equals(passwd) ){
			mem.addConvertId(id);
			save();
			return "set add id [" + id + "]";
		}else{
			return "password error";
		}
	}

	public String set_deleteconvid(String name, String passwd, String id){
		Member mem = member_list.getMember(name);
		if( mem == null ) return "member not found";

		if( mem.getPassword().equals(passwd) ){
			boolean suc = mem.deleteConvertId(id);
			if( suc ){
				save();
				return "delete complete [" + id + "]";
			}else{
				return "delete failed";
			}
		}else{
			return "password error";
		}
	}

	public Member[] get_memlist(){
		return member_list.toArray();
	}

	public String import_idlist(String passwd, String file_name){
		if( ROOT_PASSWORD.equals(passwd) ) return "password error";

		MemberIdList mem = new MemberIdList();
		try{
			mem.importCsvFile(file_name);
		}catch(IOException e0){
			return "error: " + e0.getMessage();
		}

		int cnt;
		for( cnt=0; cnt<mem.getListLength(); cnt++){

			if( member_list.isNameRegisted(mem.getNameByIndex(cnt)) ){
				member_list.getMember(mem.getNameByIndex(cnt)).addConvertId(mem.getIdByIndex(cnt));
			}else{
				Member m = new Member(mem.getNameByIndex(cnt));
				m.addConvertId(mem.getNameByIndex(cnt));
				member_list.addMember(m);
			}
		}

		save();
		return "import successfully (add " + cnt + "record(s))";
	}


}
