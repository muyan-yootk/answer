package com.jixianit.toos;

import java.io.File;
/**
 * ���ݸ�Ŀ¼����
 * @author pc
 *
 */
public class Rename {
	public static void main(String[] args) {
		// File srcDir = new File(args[0]);
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
				String fileName = file.getParent().substring(file.getParent().lastIndexOf("\\")) + ".pdf" ;
				File newFile = new File(file.getParentFile(),fileName) ;
				file.renameTo(newFile) ;
			}
		}
	}
}
