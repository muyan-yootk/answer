package com.jixianit.toos;

import java.io.File;

public class FileRenameBatch {
	public static void main(String[] args) {
		// File dir = new File(args[0]) ; ��0����
		File dir = new File("H:\\temp") ;
		rename(dir) ;
		
	}
	public static void rename(File file) {
		if(file.isDirectory()) {
			if (jpgCheck(file)) {
				File result [] = file.listFiles() ;
				if (result.length >= 10) {	// ��������
					batchRename(file,String.valueOf(result.length).length()) ;
				}
			} else {
				File result [] = file.listFiles() ;
				for (int x = 0 ; x < result.length ; x ++) {
					rename(result[x]) ;
				}
			}
		}
	}
	
	public static void batchRename(File dir,int maxLength) {
		File result [] = dir.listFiles() ;
		for (int x = 0 ; x < result.length ; x ++) {
			String newFileNameNumber = result[x].getName().substring(result[x].getName().lastIndexOf("_") + 1,result[x].getName().lastIndexOf(".")) ;
			while(newFileNameNumber.length() != maxLength) {
				newFileNameNumber = "0" + newFileNameNumber ;
			}
			String newFileName = result[x].getName().substring(0,result[x].getName().lastIndexOf("_") + 1) + newFileNameNumber + ".jpg" ;
			// System.out.println(newFileName);
			File newName = new File(result[x].getParentFile(),newFileName) ;
			result[x].renameTo(newName) ;
			// System.out.println(newName);
		}
	}
	
	public static boolean jpgCheck(File dirFile) {
		File result [] = dirFile.listFiles() ;
		int count = 0 ; 
		for (int x = 0 ; x < result.length ; x ++) {
			if (result[x].getName().endsWith(".jpg")) {
				count ++ ;
			}
		}
		return count == result.length ; 
	}
}
