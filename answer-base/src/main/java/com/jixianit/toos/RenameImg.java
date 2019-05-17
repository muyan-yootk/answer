package com.jixianit.toos;

import java.io.File;

public class RenameImg {
	public static void main(String[] args) {
		File srcDir = new File("H:\\temp");
		batchRename(srcDir) ;
	}
	public static void batchRename(File file) {
		if (file.isDirectory()) {
			File result [] = file.listFiles() ;
			if (result != null) {
				for (int x = 0 ; x < result.length ; x ++) {
					batchRename(result[x]) ;
				}
			}
		} else {
			if (file.isFile() && file.getName().endsWith(".pdf")) {
				if (file.getName().contains("_IMG")) {
					String fileName = file.getName().substring(0,file.getName().lastIndexOf("_IMG")) + ".pdf" ;
					File newFile = new File(file.getParentFile(),fileName) ;
					System.out.println(newFile);
					file.renameTo(newFile) ;
				}
			}
		}
	}
}
