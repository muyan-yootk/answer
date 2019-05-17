package com.jixianit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCCommit {
    public static final String DATABASE_DRIVER = "org.gjt.mm.mysql.Driver" ;
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mldn" ;
    public static final String DATABASE_USER = "root" ;
    public static final String DATABASE_PASSWORD = "mysqladmin" ;
    // create table member(id int auto_increment primary key,name varchar(30))engine=innodb
    public static void main(String[] args) throws Exception {
        Class.forName(DATABASE_DRIVER) ; // 加载驱动程序
        Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD) ;
        conn.setAutoCommit(false);
        String sql = "INSERT INTO member (name) VALUES (?)" ;
        PreparedStatement pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1,"LEE.HELLO");
        try {
            System.out.println(pstmt.executeUpdate());
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        }
        conn.close();
    }
}
