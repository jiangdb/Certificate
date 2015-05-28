package com.oosictech.certificate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2015/5/25.
 */
public class DeviceDao {
    private String dburl;
    private String user;
    private String passwd;
    private String driverClass;

    public DeviceDao(String dburl, String user, String passwd, String driverClass) {
        this.dburl=dburl;
        this.user=user;
        this.passwd=passwd;
        this.driverClass=driverClass;
    }

    public int getDevicesCount(String table) {
        if (table == null || table.equals("")) {
            return 0;
        }
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName(driverClass);
        }
        catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        int ret = 0;
        try {
            conn = DriverManager.getConnection(dburl, user, passwd);
            stmt = conn.createStatement();
            stmt.executeUpdate("use CarDevices");
            String sql = "select count(*) from `" + table + "`;";
            rs = stmt.executeQuery(sql);
            rs.next();
            ret = rs.getInt(1);
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                if (rs!=null){
                    rs.close();
                }
                if (stmt!=null){
                    stmt.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }

        return ret;
    }

    public List<Device> getDevices(String table, int start,int count) {
        if (count <=0 || start <0 || table == null || table.equals("")) {
            return null;
        }

        List<Device> list = new ArrayList<Device>();

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try {
            Class.forName(driverClass);
        }
        catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(dburl, user, passwd);
            stmt = conn.createStatement();
            stmt.executeUpdate("use CarDevices");
            String sql = "select * from `" + table + "` order by id desc limit " + start + "," + count + ";";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String uuid = rs.getString("uuid");
                Date createTime = rs.getDate("created");
                String createIp = rs.getString("ip");
                Device d = new Device(id, uuid, createIp,createTime);
                list.add(d);
            }
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                if (stmt!=null){
                    stmt.close();
                }
                if (rs!=null){
                    rs.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }

        return list;
    }
}
