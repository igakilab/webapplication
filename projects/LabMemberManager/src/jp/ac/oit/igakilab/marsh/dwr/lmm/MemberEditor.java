package jp.ac.oit.igakilab.marsh.dwr.lmm;

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


}
