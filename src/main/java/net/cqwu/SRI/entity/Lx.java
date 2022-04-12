package net.cqwu.SRI.entity;

import java.util.Date;

public class Lx {
    private String lid;
    private String lname;
    private String ltype;
    private Date lbtime;
    private Date letime;
    private int lmoney;
    private String laddress;
    private String lperson;
    private String uid;

    public Lx() {
    }

    public Lx(String lid, String lname, String ltype, Date lbtime, Date letime, int lmoney, String laddress, String lperson, String uid) {
        this.lid = lid;
        this.lname = lname;
        this.ltype = ltype;
        this.lbtime = lbtime;
        this.letime = letime;
        this.lmoney = lmoney;
        this.laddress = laddress;
        this.lperson = lperson;
        this.uid = uid;
    }

    public String getLperson() {
        return lperson;
    }

    public void setLperson(String lperson) {
        this.lperson = lperson;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    public Date getLbtime() {
        return lbtime;
    }

    public void setLbtime(Date lbtime) {
        this.lbtime = lbtime;
    }

    public Date getLetime() {
        return letime;
    }

    public void setLetime(Date letime) {
        this.letime = letime;
    }

    public int getLmoney() {
        return lmoney;
    }

    public void setLmoney(int lmoney) {
        this.lmoney = lmoney;
    }

    public String getLaddress() {
        return laddress;
    }

    public void setLaddress(String laddress) {
        this.laddress = laddress;
    }

    @Override
    public String toString() {
        return "Lx{" +
                "lid='" + lid + '\'' +
                ", lname='" + lname + '\'' +
                ", ltype='" + ltype + '\'' +
                ", lbtime=" + lbtime +
                ", letime=" + letime +
                ", lmoney=" + lmoney +
                ", laddress='" + laddress + '\'' +
                ", lperson='" + lperson + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
