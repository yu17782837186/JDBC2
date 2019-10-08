package cn.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@SuppressWarnings("all")
public class Demo06 {
    //测试事务
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            c.setAutoCommit(false);
//            ps1 = c.prepareStatement("insert into t_user(name,gender) values(?,?)"); //事务开始
//            ps1.setObject(1,"小军");
//            ps1.setObject(2,"男");
//            ps1.execute();
//            System.out.println("插入一个用户,小军");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ps2 = c.prepareStatement("insert into t_user(name,gender) values(?,?)");
//            ps2.setObject(1,"小小");
//            ps2.setObject(2,"女");
//            ps2.execute();
//            System.out.println("插入一个人用户，小小");
//
//            c.commit();//事务结束  要么同时成功，要么同时失败
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            c.setAutoCommit(false);
            ps1 = c.prepareStatement("insert into t_user(name,gender) values(?,?)");
            ps1.setObject(1,"小小");
            ps1.setObject(2,"女");
            ps1.execute();
            System.out.println("插入一个人用户，小小");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ps2 = c.prepareStatement("insert into t_user(name,gender) values(?,?)");
            ps2.setObject(1,"小刚");
            ps2.setObject(2,"男");
            ps2.execute();
            System.out.println("插入一个用户，小刚");
            c.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
//            try {
//                c.rollback(); //回滚
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps2 != null) {
                    ps2.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps1 != null) {
                    ps1.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
