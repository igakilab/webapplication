package jp.ac.oit.igakilab.marsh.dwr.beans;

public class MemberIdBean {
	private String name;
	private String id;

	public MemberIdBean(String n0, String i0){
		name = n0;
		id = i0;
	}

	public MemberIdBean(){
		this("", "");
	}

	public String getName(){ return name; }
	public void setName(String n0){ name = n0; }
	public String getId(){ return id; }
	public void setId(String i0){ id = i0; }
}
