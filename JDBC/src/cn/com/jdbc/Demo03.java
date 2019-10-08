package cn.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("all")
public class Demo03 {
    //测试PreparedStatement的基本用法
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
//            //加载驱动类
//            Class.forName("com.mysql.jdbc.Driver");
//            //建立连接
//            Connection c = DriverManager.getConnection
//                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            String sql = "insert into t_user(id,name,gender) values(?,?,?)"; //?占位符 防止SQL注入
//            PreparedStatement ps = c.prepareStatement(sql);
////            ps.setString(1,"小明"); //jdbc中参数索引从1 开始计算
////            ps.setString(2,"男");
//            ps.setInt(1,5);
//            ps.setString(2,"小高");
//            ps.setString(3,"男");
//
////            ps.setObject(1,"小亮");
////            ps.setObject(2,"男");
//            System.out.println("插入一行数据");
//            ps.execute();
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            String sql = "insert into t_user(id,name,gender) values(?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setObject(1,"8");
            ps.setObject(2,"小浩");
            ps.setObject(3,"男");
            ps.setObject(1,15);
            ps.setObject(2,"团团");
            ps.setObject(3,"女");
            System.out.println("执行添加语句");
//            ps.execute();
            int count = ps.executeUpdate(); //非查询语句 用executeUpdate
            System.out.println(count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
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
