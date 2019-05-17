package com.jixianit.test;

import java.util.Random;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        Random rand = new Random() ;
        String str = "mldnjava" ;
        while (true) {
            str += "abc" + rand.nextInt(999999999) + rand.nextInt(999999999) ;
            str.intern() ;				// 强制产生垃圾
        }
    }
}
