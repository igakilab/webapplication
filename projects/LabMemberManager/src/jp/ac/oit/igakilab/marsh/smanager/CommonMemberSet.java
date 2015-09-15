package jp.ac.oit.igakilab.marsh.smanager;

public class CommonMemberSet {
	public static String[][] PAIRS = {
			{"りょうくん", "ryokun"},
			{"りょうくん", "Ryokun"}
	};

	public static MemberInfoList LIST = createMemberInfoList();


	public static MemberInfoList createMemberInfoList(){
		MemberInfoList new_list = new MemberInfoList();

		for(int i=0; i<PAIRS.length; i++){
			new_list.addId(PAIRS[i][0], PAIRS[i][1]);
		}

		return new_list;
	}
}
