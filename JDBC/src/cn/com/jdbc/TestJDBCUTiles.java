package cn.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@SuppressWarnings("all")
public class TestJDBCUTiles {
    public static void main(String[] args) {
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("insert into t_user2(username,pwd) values(?,?)");
            ps.setObject(1,"晓晓");
            ps.setObject(2,"123456");
            ps.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
    }
}
