package com.propn.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CharIO {

	public static void main(String[] args) throws IOException {

		// FileWriter fw = new FileWriter("D:/2.txt");
		// String encode = fw.getEncoding();//FileWriter和FileReader使用系统当前默认的编码方式
		// fw.write("abcdefg");
		// fw.flush();
		// fw.close();
		//
		// //
		// FileOutputStream fos=new FileOutputStream("D:/3.txt");
		// OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF8");
		// BufferedWriter bw = new BufferedWriter(osw);
		// bw.write("abcdefg");
		// bw.flush();
		// bw.close();

		// FileReader fr = new FileReader("D:/6.txt");
		// fr.read()
		//
		// CharBuffer cbuf = null;
		// fr.read(cbuf);
		//
		//
		//
		// BufferedReader br = new BufferedReader(fr);

		File file = new File("D:/3.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一行");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				// 把当前行号显示出来
				//System.out.println(tempString);
				
				int lastIndexOf = tempString.lastIndexOf("/")+1;
				String substring = tempString.substring(0, lastIndexOf); //folder
				//System.out.println("md "+substring);
				
				System.out.println("copy "+tempString.substring(3)+" "+substring);
				
				
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
