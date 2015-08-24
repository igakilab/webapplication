/*機能つけすぎでぐちゃぐちゃになったやつ*/

package jp.ac.oit.igakilab.marsh.smanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LogRecorder {
	public static final int MODE_READ = 101;
	public static final int MODE_WRITE = 102;
	public static final int MODE_ADD = 103;

	static DateFormat gdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static BufferedReader createReader(String fname)
	throws FileNotFoundException {
		return new BufferedReader(
			new FileReader(fname)
		);
	}

	private static PrintWriter createWriter(String fname, boolean add)
	throws IOException {
		return new PrintWriter(
			new BufferedWriter(
				new FileWriter(fname, add)
			)
		);
	}


	/**********/

	BufferedReader brd;
	PrintWriter pwt;
	int mode;
	String file_dest;


	public LogRecorder(String f0, int m0){
		brd = null;
		pwt = null;
		mode = m0;
		file_dest = f0;
	}


	public void openFile() throws FileNotFoundException, IOException {
		switch( mode ){
		case MODE_READ:
			brd = createReader(file_dest);
			break;
		case MODE_WRITE:
			pwt = createWriter(file_dest, false);
			break;
		case MODE_ADD:
			pwt = createWriter(file_dest, true);
			break;
		}
	}

	public void closeFile() throws IOException{
		switch( mode ){
		case MODE_READ:
			brd.close();
			break;
		case MODE_WRITE:
		case MODE_ADD:
			pwt.close();
			break;
		}
	}


	public int read() throws IOException{
		int val = 0;

		if( mode == MODE_READ ){
			if( brd != null ){
				val = brd.read();
			}
		}

		return val;
	}


	public String readLine() throws IOException{
		String str = "";

		if( mode == MODE_READ ){
			if( brd != null ){
				str = brd.readLine();
			}
		}

		return str;
	}


	public void print(String str, boolean auto_open) throws IOException{
		boolean auto_close = false;

		if( pwt == null ){
			if( auto_open ){
				openFile();
				auto_close = true;
			}else{
				return;
			}
		}

		if( pwt == null ){
			pwt.print(str);
		}

		if( auto_close ){
			closeFile();
		}
	}


	public void println(String str, boolean auto_open) throws IOException{
		str += "\n";
		print(str, auto_open);
	}


	public void print(String str) throws IOException{
		print(str, false);
	}


	public void println(String str) throws IOException{
		println(str, false);
	}

}
