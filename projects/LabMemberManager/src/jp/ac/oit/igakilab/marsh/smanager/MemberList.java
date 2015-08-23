package jp.ac.oit.igakilab.marsh.smanager;

import java.util.ArrayList;
import java.util.List;

public class MemberList {
	/*インスタンス変数*/
	List <MemberInfo> mlist;
	LogRecorder logrec;


	/*コンストラクタ*/
	public MemberList(){
		mlist = new ArrayList<MemberInfo>();
	}

	public MemberList(LogRecorder rec){
		this();
		logrec = rec;
		addLog("MEMBERLIST GENERATED");
	}


	/*内部的メソッド*/
	int searchMemberInfoByName(String name){
		for(int i=0; i<mlist.size(); i++){
			if( name.equals(mlist.get(i).getName()) ){
				return i;
			}
		}
		return -1;
	}

	void addLog(String msg){
		if( logrec != null ){
			logrec.addSingleLog("MemberList: " + msg, true);
		}
	}

	/*操作メソッド*/
	public void addMemberInfo(MemberInfo mi){
		if( searchMemberInfoByName(mi.getName()) < 0 ){
			mlist.add(mi);
			addLog(String.format("<name:%s> added", mi.getName()));
		}
	}

	public void setMemberInfo(MemberInfo mi){
		int idx = searchMemberInfoByName(mi.getName());

		if( idx >= 0 ){
			mlist.get(idx).setMemberInfo(mi);
			mlist.get(idx).updateDate();
			addLog(String.format("<name:%s> updated", mi.getName()));
		}else{
			addMemberInfo(mi);
		}
	}

	public void deleteMemberInfo(int idx){
		if( idx >= 0 && idx < mlist.size() ){
			addLog(String.format("<name:%s> deleted", mlist.get(idx).getName()));
			mlist.remove(idx);
		}
	}

	public void deleteMemberInfo(String name){
		int idx = searchMemberInfoByName(name);

		if( idx >= 0 ){
			deleteMemberInfo(idx);
		}
	}

	public MemberInfo getMemberInfo(int idx){
		if( idx >= 0 && idx < mlist.size() ){
			return mlist.get(idx);
		}else{
			return null;
		}
	}

	public MemberInfo getMemberInfo(String name){
		int idx = searchMemberInfoByName(name);

		if( idx >= 0 ){
			return getMemberInfo(idx);
		}else{
			return null;
		}
	}

	public boolean isMemberInfoRegisted(String name){
		return searchMemberInfoByName(name) >= 0;
	}


	/*情報取得メソッド*/
	public void setLogRecorder(LogRecorder recorder){
		logrec = recorder;
	}

	public int getMemberListLength(){
		return mlist.size();
	}
}
