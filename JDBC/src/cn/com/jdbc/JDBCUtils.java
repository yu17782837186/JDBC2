package cn.com.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings("all")
public class JDBCUtils {
    static Properties pros = null;//可以帮助和读取资源文件中的信息
    static { //加载这个类时候调用
        pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获得mysql的连接
    public static Connection getMySqlCoon() {
        try {
            Class.forName(pros.getProperty("mysqldriver"));
            return DriverManager.getConnection
                    (pros.getProperty("mysqlURL"),pros.getProperty("mysqlUser"),pros.getProperty("mysqlPwd"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void close(ResultSet rs, Statement ps, Connection coon) {
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
            if (coon != null) {
                coon.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement ps, Connection coon) {
        try {
            if (ps != null) {
                ps.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if (coon != null) {
                coon.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection coon) {
        try {
            if (coon != null) {
                coon.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs, Statement ps) {
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
    }

}
