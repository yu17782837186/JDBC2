package cn.com.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("all")
public class Demo05 {
    //测试批处理操作
    public static void main(String[] args) {
        Connection c = null;
        Statement st = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            c = DriverManager.getConnection
//                    ("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//            c.setAutoCommit(false);//设为手动提交
//            long start = System.currentTimeMillis();
//            st = c.createStatement();
//            for (int i = 0; i < 10000; i++) {
//                st.addBatch("insert into t_user(name,gender) values('余"+i+"','男')");
//            }
//            st.executeBatch();
//            c.commit();//提交事务
//            long end = System.currentTimeMillis();
//            System.out.println("插入10000行数据，耗时："+(end - start));
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            c.setAutoCommit(false);
            st = c.createStatement();
            for (int i = 0; i < 200; i++) {
                st.addBatch("");
            }
            st.executeBatch();
            c.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
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
