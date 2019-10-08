package cn.com.jdbc;

import java.io.*;
import java.sql.*;

@SuppressWarnings("all")
public class Demo09 {
    //测试CLOB 文本大对象的使用 包含将字符串，
    // 文件内容插入到数据库中的CLOB字段中，或者将CLOB字段中的值取出来
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reader r = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            ps = c.prepareStatement("insert into t_user2(username,myinfo) values(?,?)");
//            ps.setString(1,"小明");
            //将文本文件内容直接输入到数据库中
//            ps.setClob(2,new FileReader("F:/a.txt"));

            //将程序中的字符串输入到数据库中的Clob字段中
//            ps.setClob(2,new BufferedReader(new InputStreamReader(new ByteArrayInputStream("zxcvbn".getBytes()))));
//            ps.executeUpdate();

//            ps = c.prepareStatement("select * from t_user2 where id = ?");
//            ps.setObject(1,"1009");
//            rs = ps.executeQuery();
//            while(rs.next()) {
//               Clob clob = rs.getClob("myinfo");
//               Reader r = clob.getCharacterStream();
//               int tmp;
//               while((tmp = r.read()) != -1) {
//                   System.out.print((char)tmp);
//               }
//            }

            ps = c.prepareStatement("select * from t_user2 where id > ?");
            ps.setObject(1,"1009");
            rs = ps.executeQuery();
            while(rs.next()) {
                Clob clob = rs.getClob("myinfo");
                r = clob.getCharacterStream();
                int tmp;
                while((tmp = r.read()) != -1) {
                    System.out.print((char)tmp);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (r != null) {
                    r.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
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
