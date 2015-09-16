package jp.ac.oit.igakilab.marsh.smanager;

public class CommonMemberSet {
	public static String[][] PAIRS = {
			{"りょーくん", "ryokun"},
			{"りょーくん", "Ryokun"},
			{"しにゃ", "Shinya"},
			{"しにゃ", "shinya"},
			{"きたば", "Kitaba"},
			{"きたば", "kitaba"}
	};

	public static MemberIdList LIST = createMemberIdList();


	public static MemberIdList createMemberIdList(){
		MemberIdList new_list = new MemberIdList();

		for(int i=0; i<PAIRS.length; i++){
			new_list.addId(PAIRS[i][0], PAIRS[i][1]);
		}

		return new_list;
	}
}
