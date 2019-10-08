package cn.com.jdbc;

import java.io.*;
import java.sql.*;

@SuppressWarnings("all")
public class Demo10 {
    //测试BLOB 二进制大对象的使用
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            ps = c.prepareStatement("insert into t_user2(username,headImg) values(?,?)");
//            ps.setObject(1,"小红");
//            ps.setBlob(2,new FileInputStream("F:/ct.jpg"));
//            ps.execute();

            ps = c.prepareStatement("select * from t_user2 where id = ?");
            ps.setObject(1,"1011");
            rs = ps.executeQuery();
            while(rs.next()) {
                Blob b = rs.getBlob("headImg");
                is = b.getBinaryStream();
                os = new FileOutputStream("F:/img.jpg");
                int tmp = 0;
                while((tmp = is.read()) != -1) {
                    os.write(tmp);
                }
                os.flush();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
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
