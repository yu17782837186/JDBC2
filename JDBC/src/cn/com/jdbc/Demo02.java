package cn.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("all")
public class Demo02 {
    //测试执行SQL语句以及SQL注入问题
    public static void main(String[] args) {
        Connection c = null;
        Statement st = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            st = c.createStatement();
//            String sql = "insert into t_user(id,name,gender) values(4,\"小红\",\"女\")";

            String name = "小军";
            String sql = "insert into t_user(id,name,gender) values(5,'"+name+"','男')";//字符串只能拼接的形式
            st.execute(sql);

            //测试SQL注入
//            String id = "5 or 1 = 1";
//            String sql = "delete from t_user where id ="+id;
//            st.execute(sql);
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
