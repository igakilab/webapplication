package jp.ac.oit.igakilab.dwr.multiple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.MemberInfo;
import jp.ac.oit.igakilab.marsh.smanager.MemberInfoBean;
import jp.ac.oit.igakilab.marsh.smanager.StateManager;

public class MultiplePrinter {
	StateManager list;

	public MultiplePrinter(){
		list = new StateManager();
	}


	public String login(String name){
		String ret_str;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		list.addMember(name, StateManager.STATE_IN);
		list.updateStateList();

		ret_str = String.format("<%s> signined (%s)",
			name,
			df.format(list.getMemberInfoByName(name).getUpdateDate().getTime())
		);

		ret_str += "[number of member = " + list.getMemberNum() + "]";

		return ret_str;
	}


	public List<String> getMemberList(){
		List<String> m_list = new ArrayList<String>();
		int list_len;
		MemberInfo inf;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		list.updateStateList();
		list_len = list.getMemberNum();
		for(int i=0; i<list_len; i++){
			inf = list.getMemberInfo(i);
			m_list.add(
				String.format("%s(%d, %s(%d))",
					inf.getName(),
					inf.getState(),
					df.format(inf.getUpdateDate().getTime()),
					inf.getUpdateDate().getTime().getTime()
				)
			);
		}

		return m_list;
	}


	public List<MemberInfoBean> getMemberInfoList(){
		List<MemberInfoBean> m_list = new ArrayList<>();
		int list_len;

		list.updateStateList();
		list_len = list.getMemberNum();
		for(int i=0; i<list_len; i++){
			m_list.add(new MemberInfoBean(list.getMemberInfo(i)));
		}

		return m_list;
	}


	public String getMemberListString(){
		StringBuffer sbuf = new StringBuffer();
		List<String> m_list = getMemberList();

		for(int i=0; i<m_list.size(); i++){
			sbuf.append("[" + i + ": " + m_list.get(i) + "] ");
		}

		return sbuf.toString();
	}


	/* 古いメソッドたち */

	public String helloWorld(String name){
		return name + ":HelloWorld";
	}

    /**
     * 1から10までのすべての数値について，
     * 以下の条件を満たしながら小さい順にリスト化して返す
     *   条件1：数値が3の倍数のときは”ryokun”をリストに追加する．
     * @return 1から10までの数値を変換した文字列のリスト
     */
    public List<String> execute(MultipleForm input) throws InvalidValueException {
        List<String> list = new ArrayList<>();
        int max = input.getMax();
        int multiple = input.getMultiple();


        if (multiple < 0 || max < 0){
        	throw new InvalidValueException("倍数は 正の整数(>0)でなければいけません．現在の値：" + multiple);
        }
        for(int i= 1; i<=max; i++){
        	if(i % multiple == 0){
        		list.add("ryokun");
        	}else{
        		list.add(Integer.toString(i));
        	}
        }

        return list;
    }




}
