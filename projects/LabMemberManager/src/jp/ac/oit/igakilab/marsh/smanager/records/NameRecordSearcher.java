package jp.ac.oit.igakilab.marsh.smanager.records;

import java.util.ArrayList;
import java.util.List;

import jp.ac.oit.igakilab.marsh.smanager.MemberIdList;

public class NameRecordSearcher
implements RecordSearcher {
	//フィールド
	private String target_name;
	List<String> ids;
	RecordList list;
	MemberIdList idlist;

	//コンストラクタ
	public NameRecordSearcher(){
		instance_init();
	};

	public NameRecordSearcher(String i0){
		this();
		target_name = i0;
	}

	//初期化
	public void instance_init(){
		target_name = null;
		list = new RecordList();
	}

	//get/set
	public String getTargetName(){
		return target_name;
	}
	public void setTargetName(String i0){
		target_name = i0;
	}

	//オブジェクト取得
	public RecordList getRecordList(){
		return list;
	}

	//リスト操作
	public void clearList(){
		list.clear();
	}

	//実装メソッド
	public boolean isExcutable(){
		return target_name != null && idlist != null;
	}

	public void init(){
		ids = new ArrayList<String>();
		String[] tmp = idlist.getIdByName(target_name);
		for(int i=0; i<tmp.length; i++) ids.add(tmp[i]);
		ids.add(target_name); //idだけでなく名前も検索対象に含む
		list.clear();
	}

	public int excute(RecordList r0){
		for(int i=0; i<r0.getRecordCount(); i++){
			if( ids.contains(r0.getRecord(i).getId()) ){
				list.addRecord(r0.getRecord(i));
			}
		}

		return RecordSearcher.EX_CONTINUE;
	}
}
