package com.oosictech.certificate;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Home on 2015/5/25.
 */
public class Device {
    private int id;
    private String uuid;
    private String createIp;
    private String createTime;

    public Device(int id, String uuid, String createIp, Timestamp createTime) {
        super();
        this.id = id;
        this.uuid = uuid;
        this.createIp = createIp;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = df.format(createTime);
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUUID(){
        return uuid;
    }

    public void setUUID(String uuid){
        this.uuid = uuid;
    }

    public String getCreateIp(){
        return createIp;
    }

    public void setCreateIp(String ip){
        this.createIp = ip;
    }

    public String getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Timestamp createTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = df.format(createTime);
    }
}
