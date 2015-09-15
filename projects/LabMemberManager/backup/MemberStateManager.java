package jp.ac.oit.igakilab.marsh.smanager;

public class MemberStateManager {
	/*スタティック変数*/
	public static final int STATE_LOGIN = MemberInfo.STATE_LOGIN;
	public static final int STATE_LOGOUT = MemberInfo.STATE_LOGOUT;
	public static final int STATE_UNDEF = MemberInfo.STATE_UNDEF;


	/*インスタンス変数*/
	RecordList records;


	/*コンストラクタ*/
	public MemberStateManager(){
		records = new RecordList();
	}


	/*操作メソッド*/
	public void addMemberState(String name, int state_code){
		records.addRecord(new ActionRecord(name, state_code));
	}


	/*取得メソッド*/
	public MemberInfo getMemberInfo(String name){
		if( records.isNameRegisted(name) ){
			return new MemberInfo(name, records);
		}else{
			return null;
		}
	}


	public String[] getMemberNameList(){
		return records.getNameList();
	}


	public MemberInfo[] getMemberInfoList(){
		String[] names = getMemberNameList();
		MemberInfo[] minf = new MemberInfo[names.length];

		for(int i=0; i<names.length; i++){
			minf[i] = new MemberInfo(names[i], records);
		}

		return minf;
	}


	public int getMemberCount(){
		return records.getNameList().length;
	}


	/*各変数返却メソッド*/
	public RecordList getRecordListObject(){
		return records;
	}


	public StateList getStateList(){
		return MemberInfo.getStateList();
	}

}