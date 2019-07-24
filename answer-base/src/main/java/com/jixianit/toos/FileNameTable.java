package com.jixianit.toos;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameTable {
    public static final String URL = "http://video.yootk.com";
    public static final String DIRECTORY = "/java11/";

    public static void main(String[] args) throws Exception {
        File dir = new File("H:\\java11");
        String[] list = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp4");
            }
        });
        for (int x = 0; x < list.length; x++) {
            String id = getId(x + 1, list.length);
            String newName = list[x].substring(0, 5) + "." + list[x].substring(list[x].lastIndexOf(".") + 1);
            String url = URL + DIRECTORY + newName;
            System.out.println(String.format("%s\t%s\t%s\t%s\t%s", id, newName, list[x], url,"desc"));
        }
    }

    public static String getId(int id, int max) {
        StringBuffer buf = new StringBuffer();
        buf.append(id) ;
        while (buf.length() < String.valueOf(max).length()) {
            buf.insert(0, "0");
        }
        return buf.toString();
    }
}
