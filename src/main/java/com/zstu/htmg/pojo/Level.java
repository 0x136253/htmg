package com.zstu.htmg.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Level implements Serializable {
    private Integer id;
    private String levelname;
    private Integer levelnum;
    private BigDecimal leveloffsetradio;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public Integer getLevelnum() {
        return levelnum;
    }

    public void setLevelnum(Integer levelnum) {
        this.levelnum = levelnum;
    }

    public BigDecimal getLeveloffsetradio() {
        return leveloffsetradio;
    }

    public void setLeveloffsetradio(BigDecimal leveloffsetradio) {
        this.leveloffsetradio = leveloffsetradio;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", levelname='" + levelname + '\'' +
                ", levelnum=" + levelnum +
                ", leveloffsetradio=" + leveloffsetradio +
                '}';
    }
}