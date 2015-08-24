package jp.ac.oit.igakilab.marsh.dwr.lmm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.LogRecorder;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfo;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfoBean;
import jp.ac.oit.igakilab.marsh.smanager.MemberList;
import jp.ac.oit.igakilab.marsh.smanager.StateList;

public class LmmManager {
	static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	MemberList list;
	LogRecorder logrec;

	/*コンストラクター*/
	public LmmManager(){
		logrec = new LogRecorder("logs.txt", true);
		addLog("Lmmマネージャが開始しました");
		list = new MemberList(logrec);
	}

	void addLog(String msg){
		if( logrec != null ){
			logrec.addSingleLog("LMMM: " + msg, true);
		}
	}

	public String login(String name){
		MemberInfo inf;

		if( list.isMemberInfoRegisted(name) ){
			inf = list.getMemberInfo(name);
			inf.setStateCode(MemberList.STATE_LOGIN);
			inf.updateDate();
			addLog("<name:" + name + "> state updated");
		}else{
			inf = new MemberInfo(name, MemberList.STATE_LOGIN);
			list.addMemberInfo(inf);
			addLog("<name:" + name + "> new member added");
		}

		list.updateMemberList();

		return "<name:" + name + "> logined [number of member = " + list.getMemberListLength() + "]";
	}

	public List<String> getMemberListInStringList(){
		List<String> ml = new ArrayList<String>();
		StateList sl = list.getStateList();
		MemberInfo tmp;

		list.updateMemberList();

		for(int i=0; i<list.getMemberListLength(); i++){
			tmp = list.getMemberInfo(i);
			ml.add(
				String.format("[%2d] NAME:%s, STATE:%s, LOGIN:%s, UPDATE:%s",
					i,
					tmp.getName(),
					sl.getStateName(tmp.getStateCode()),
					DF.format(tmp.getLoginDate()),
					DF.format(tmp.getUpdateDate())
				)
			);
		}

		return ml;
	}

	public List<MemberInfoBean> getMemberList(){
		List<MemberInfoBean> ml = new ArrayList<MemberInfoBean>();
		StateList sl = list.getStateList();

		list.updateMemberList();

		for(int i=0; i<list.getMemberListLength(); i++){
			ml.add(new MemberInfoBean(list.getMemberInfo(i), sl));
		}

		return ml;
	}
}
