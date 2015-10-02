package jp.ac.oit.igakilab.marsh.smanager;

public class CommonStateSet {
	public static final int LOGIN = 101;
	public static final int LECTURE = 102;
	public static final int CONVENI = 103;
	public static final int LOGOUT = 104;

	public static final int UNDEFINED = 191;

	public static StateList LIST = createStateList();


	public static StateList createStateList(){
		StateList new_list = new StateList();

		new_list.addState(new StateInfo(LOGIN, "LOGIN", 360));
		new_list.addState(new StateInfo(LOGOUT, "LOGOUT", 0));
		new_list.addState(new StateInfo(LECTURE, "LECTURE", 54000));
		new_list.addState(new StateInfo(CONVENI, "CONVENIENCE STORE", 1200));

		return new_list;
	}
}
