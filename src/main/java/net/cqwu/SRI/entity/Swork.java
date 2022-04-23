package net.cqwu.SRI.entity;

import java.io.Serializable;
import java.util.Date;

public class Swork implements Serializable {
    private String swid;
    private String swname;
    private String swperson;
    private String swaddress;
    private Date swtime;
    private String uid;

    public String getSwid() {
        return swid;
    }

    public void setSwid(String swid) {
        this.swid = swid;
    }

    public String getSwname() {
        return swname;
    }

    public void setSwname(String swname) {
        this.swname = swname;
    }

    public String getSwperson() {
        return swperson;
    }

    public void setSwperson(String swperson) {
        this.swperson = swperson;
    }

    public String getSwaddress() {
        return swaddress;
    }

    public void setSwaddress(String swaddress) {
        this.swaddress = swaddress;
    }

    public Date getSwtime() {
        return swtime;
    }

    public void setSwtime(Date swtime) {
        this.swtime = swtime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Swork{" +
                "swid='" + swid + '\'' +
                ", swname='" + swname + '\'' +
                ", swperson='" + swperson + '\'' +
                ", swaddress='" + swaddress + '\'' +
                ", swtime=" + swtime +
                ", uid='" + uid + '\'' +
                '}';
    }
}
