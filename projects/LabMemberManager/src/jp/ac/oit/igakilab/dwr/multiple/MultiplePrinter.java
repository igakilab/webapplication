package jp.ac.oit.igakilab.dwr.multiple;

import java.util.ArrayList;
import java.util.List;

public class MultiplePrinter {


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
