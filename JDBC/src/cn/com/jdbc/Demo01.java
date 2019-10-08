package cn.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@SuppressWarnings("all")
public class Demo01 {
    public static void main(String[] args) {
        Connection coon = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            long start = System.currentTimeMillis();
            //建立连接 连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时（这是Connection对象管理的一个要点）
            coon = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            System.out.println(coon);
            long end = System.currentTimeMillis();
            System.out.println("建立连接，耗时："+(end-start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (coon != null) {
                    coon.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
