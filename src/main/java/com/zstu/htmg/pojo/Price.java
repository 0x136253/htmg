package com.zstu.htmg.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Price implements Serializable {
    private Integer id;
    private BigDecimal baseprice;
    private BigDecimal offsetradio;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(BigDecimal baseprice) {
        this.baseprice = baseprice;
    }

    public BigDecimal getOffsetradio() {
        return offsetradio;
    }

    public void setOffsetradio(BigDecimal offsetradio) {
        this.offsetradio = offsetradio;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", baseprice=" + baseprice +
                ", offsetradio=" + offsetradio +
                '}';
    }
}