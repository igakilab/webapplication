package jp.ac.oit.igakilab.marsh.smanager;

public class StateInfo {
	/*インスタンス変数*/
	private int code;
	private String name;
	private int timeout;


	/*コンストラクタ*/
	public StateInfo(int c0, String n0, int t0){
		code = c0;
		name = n0;
		timeout = t0;
	}

	/*メソッド(get/set)*/
	public int getCode(){
		return code;
	}
	public void setCode(int c0){
		code = c0;
	}

	public String getName(){
		return name;
	}
	public void setName(String n0){
		name = n0;
	}

	public int getTimeout(){
		return timeout;
	}
	public void setTimeout(int t0){
		timeout = t0;
	}

	/*メソッド*/
	public String toString(){
		return String.format("[SI - code:%d, name:%s, timeout:%d]",
			code, name, timeout
		);
	}
}
