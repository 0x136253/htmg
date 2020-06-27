package com.zstu.htmg.pojo;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Integer id;
    private String userid;
    private String operationname;
    private String classname;
    private String method;
    private String params;
    private String databaseName;
    private Date operationtime;
    private String ip;
    private Boolean issuccess;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname == null ? null : operationname.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName == null ? null : databaseName.trim();
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Boolean getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Boolean issuccess) {
        this.issuccess = issuccess;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", operationname='" + operationname + '\'' +
                ", classname='" + classname + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", operationtime=" + operationtime +
                ", ip='" + ip + '\'' +
                ", issuccess=" + issuccess +
                '}';
    }
}