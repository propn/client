package com.propn.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteIO {

	// 1. 字节流写
	public void write() throws IOException {
		String path1 = "D:/1.txt";

		File file1 = new File(path1);

		if (!file1.exists()) {
			file1.createNewFile();
		}

		FileOutputStream fos = new FileOutputStream(file1, false);
		// byte[] b= new byte[] {'a','b','c','d','e'};
		String aa = "abcdefg012345徐abcdefg12345";
		byte[] b = aa.getBytes();
		fos.write(b); // 写byte[]
		fos.flush();

		fos.write("\r\n".getBytes());// 换行符号
		fos.write("徐雷".getBytes());// 换行符号
		byte[] b2 = aa.getBytes();
		fos.write(b2); // 写byte[]
		fos.flush();

		System.out.println("Total " + String.valueOf(b.length) + " bytes are written to stream.");

		// debug
		for (byte i : b) {
			System.out.println(i + "	" + (char) i);
		}

		fos.write((int) 'a');// 一个byte
		fos.close();
	}

	// 2. 字节流读
	public void read() throws IOException {
		String path2 = "D:/2.txt";
		File file2 = new File(path2);
		if (!file2.exists()) {
			file2.createNewFile();
		}
		FileInputStream fis = new FileInputStream(file2);

		// byte[] b = new byte[fis.available()];// 全部读到数组，中文不会乱码
		// fis.read(b);
		// System.out.println(new String(b));
		int n = 50;// 每次读取个数，可能出现中文乱码（3个字节）
		byte buffer[] = new byte[n];
		while (fis.read(buffer, 0, n) != -1) {
			System.out.print(new String(buffer));
		}

		System.out.println();
		fis.close();//
	}

	public void bufferRead() throws IOException {
		int DEFAULT_BUFFER_SIZE = 8192;
		byte buf[] = new byte[DEFAULT_BUFFER_SIZE];
		FileInputStream fis = new FileInputStream("D:/1.txt");
		BufferedInputStream bis = new BufferedInputStream(fis, DEFAULT_BUFFER_SIZE);

		bis.read(buf);
		// bis.read(buf);

	}

	public void bufferRrite() {

	}

	public static void main(String[] args) throws IOException {

		ByteIO byteio = new ByteIO();
		byteio.write();
		byteio.read();
	}

}
