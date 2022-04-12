package net.cqwu.SRI.entity;

import java.util.Date;

public class Awork {
    private int awid;
    private String awname;
    private String awperson;
    private String awpress;
    private Date awtime;
    private String awaddress;
    private String uid;

    public int getAwid() {
        return awid;
    }

    public void setAwid(int awid) {
        this.awid = awid;
    }

    public String getAwname() {
        return awname;
    }

    public void setAwname(String awname) {
        this.awname = awname;
    }

    public String getAwperson() {
        return awperson;
    }

    public void setAwperson(String awperson) {
        this.awperson = awperson;
    }

    public String getAwpress() {
        return awpress;
    }

    public void setAwpress(String awpress) {
        this.awpress = awpress;
    }

    public Date getAwtime() {
        return awtime;
    }

    public void setAwtime(Date awtime) {
        this.awtime = awtime;
    }

    public String getAwaddress() {
        return awaddress;
    }

    public void setAwaddress(String awaddress) {
        this.awaddress = awaddress;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Awork{" +
                "awid=" + awid +
                ", awname='" + awname + '\'' +
                ", awperson='" + awperson + '\'' +
                ", awpress='" + awpress + '\'' +
                ", awtime=" + awtime +
                ", awaddress='" + awaddress + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
