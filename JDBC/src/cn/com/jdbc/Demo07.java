package cn.com.jdbc;

import java.sql.*;
import java.util.Random;

@SuppressWarnings("all")
public class Demo07 {
    //测试时间处理(java.sql.Date,Time,Timestamp)
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            for (int i = 0; i < 1000; i++) {
                ps = c.prepareStatement("insert into t_user2(username,pwd,regTime,lastLoginTime) values(?,?,?,?)");
                ps.setObject(1, "小玉"+i);
                ps.setObject(2, "1237896");
                int rand = 100000000+new Random().nextInt(100000000);
                Date date = new Date(System.currentTimeMillis()-rand);
                Timestamp ts = new Timestamp(System.currentTimeMillis()-rand);
                //如果需要指定日期可以使用Canlendar，DataFormat
                ps.setDate(3, date);
                ps.setTimestamp(4, ts);
                ps.execute();
            }
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
