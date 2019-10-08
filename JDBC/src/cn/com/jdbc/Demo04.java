package cn.com.jdbc;

import java.sql.*;

@SuppressWarnings("all")
public class Demo04 {
    //测试ResultSet结果集的基本用法
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection c = DriverManager.getConnection
//                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            String sql = "select id,name,gender from t_user where id > ?";
//            PreparedStatement ps = c.prepareStatement(sql);
//            ps.setObject(1,2); //把id大于2的记录取出来
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                System.out.println(rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
//            }
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            String sql = "select id,name,gender from t_user where id > ?";
            ps = c.prepareStatement(sql);
            ps.setObject(1,2);
            rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getInt(1)+"--"+rs.getString(2)+"--"+rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
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
