package com.osf.test.io;

import java.io.File;

public class ShowFileList {

	public static void main(String[] args) {
		File tFile = new File("d:\\test");
		File[] files = tFile.listFiles();
		for (int i = 0; i < files.length; i++) {
//			if (f.isDirectory()) {
//				File[] subFiles = f.listFiles();
//				for (int j = 0; j < f.length(); j++) {
//					System.out.println(subFiles[j].getName());
//				}
//			}else {
//				System.out.println(files[i].getName());
			File f = files[i];
			System.out.println(files[i].getName());
			if (f.isDirectory()) {
				File[] subFiles = f.listFiles();
				for (int j = 0; j < subFiles.length; j++) {
					System.out.println(files[i].getName() + "/" + subFiles[j].getName());
				}
			}else {
				
			}			
		}
	}
}
