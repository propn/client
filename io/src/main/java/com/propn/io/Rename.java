package com.propn.io;

import java.io.File;

public class Rename {

	public static void main(String[] args) {
		File depot = new File("D:\\Lei\\Desktop\\New folder\\SAS Software Depot31");
		listdir(depot);
	}
	
	static void listdir(File file) {
		File[] list = file.listFiles();
		for (File f : list) {
			String name = f.getName();
			if (f.isDirectory()) {
				listdir(f);
			} else {
				int idx = name.lastIndexOf(".");
				if (idx > 3) {
					if (name.subSequence(idx - 3, idx).equals("(1)")) {
						String path = f.getPath();
						String newpath = path.replace("(1)", "");
						f.renameTo(new File(newpath));
						System.out.println(path + " " + newpath);
					}
				}
			}
		}
	}
	
}
