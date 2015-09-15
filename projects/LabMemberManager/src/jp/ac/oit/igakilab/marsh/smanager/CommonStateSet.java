package jp.ac.oit.igakilab.marsh.smanager;

public class CommonStateSet {
	public static final int LOGIN = 101;
	public static final int LOGOUT = 102;
	public static final int LECTURE = 103;
	public static final int TOILET_1 = 104;
	public static final int TOILET_2 = 105;

	public static final int UNDEFINED = 191;

	public static StateList LIST = createStateList();


	public static StateList createStateList(){
		StateList new_list = createStateList();

		new_list.addState(new StateInfo(LOGIN, "LOGIN", 360));
		new_list.addState(new StateInfo(LOGOUT, "LOGOUT", 0));
		new_list.addState(new StateInfo(LECTURE, "LECTURE", 54000));
		new_list.addState(new StateInfo(TOILET_1, "TOILET_1", 180));
		new_list.addState(new StateInfo(TOILET_2, "TOILET_2", 600));

		return new_list;
	}
}
