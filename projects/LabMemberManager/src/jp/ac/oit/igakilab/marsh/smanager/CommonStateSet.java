package jp.ac.oit.igakilab.marsh.smanager;

public class CommonStateSet {
	public static final int LOGIN_5M = 102;
	public static final int LOGIN = 101;
	public static final int LECTURE = 103;
	public static final int CONVENI = 104;
	public static final int LOGOUT = 105;

	public static final int UNDEFINED = 191;

	public static StateList LIST = createStateList();


	public static StateList createStateList(){
		StateList new_list = new StateList();

		new_list.addState(new StateInfo(LOGIN, "LOGIN", 0));
		new_list.addState(new StateInfo(LOGIN_5M, "LOGIN(5MIN)", 360));
		new_list.addState(new StateInfo(LOGOUT, "LOGOUT", 0));
		new_list.addState(new StateInfo(LECTURE, "LECTURE", 54000));
		new_list.addState(new StateInfo(CONVENI, "CONVENIENCE STORE", 1200));

		return new_list;
	}
}
