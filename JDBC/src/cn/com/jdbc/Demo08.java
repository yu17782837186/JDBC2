package cn.com.jdbc;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("all")
public class Demo08 {
    //将字符串代表的日期转为long数字(格式：yyyy-MM-dd hh:mm:ss)
    public static long strDate(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return df.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    //测试时间处理(java.sql.Date,Time,Timestamp),取出指定时间段的数据
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            ps = c.prepareStatement("insert into t_user2(username,pwd,regTime,lastLoginTime) values(?,?,?,?)");
//            ps.setObject(1,"小军");
//            ps.setObject(2,"6666666");
//            Date date = new Date(System.currentTimeMillis());
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            ps.setDate(3,date);
//            ps.setTimestamp(4,ts);
//            ps.execute();


//            ps = c.prepareStatement("select * from t_user2 where regTime > ? and regTime < ?");
//            Date start = new Date(strDate("2019-10-6 15:04:35"));
//            Date end = new Date(strDate("2019-10-8 15:04:35"));
//            ps.setObject(1,start);
//            ps.setObject(2,end);
            ps = c.prepareStatement("select * from t_user2 where lastLoginTime > ? and lastLoginTime < ? order by lastLoginTime");
            Timestamp start = new Timestamp(strDate("2019-10-8 8:10:30"));
            Timestamp end = new Timestamp(strDate("2019-10-8 15:10:30"));
            ps.setObject(1,start);
            ps.setObject(2,end);
            rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getTimestamp("lastLoginTime"));
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
