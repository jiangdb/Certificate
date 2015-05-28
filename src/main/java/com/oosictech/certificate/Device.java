package com.oosictech.certificate;

import java.sql.Date;

/**
 * Created by Home on 2015/5/25.
 */
public class Device {
    private int id;
    private String uuid;
    private String createIp;
    private Date createTime;

    public Device(int id, String uuid, String createIp, Date createTime) {
        super();
        this.id = id;
        this.uuid = uuid;
        this.createIp = createIp;
        this.createTime = createTime;
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

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}
