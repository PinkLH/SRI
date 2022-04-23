package net.cqwu.SRI.entity;

import java.io.Serializable;
import java.util.Date;

public class Hx implements Serializable {
    private int hid;
    private String hname;
    private String hobject;
    private Date hbtime;
    private Date hetime;
    private int hmoney;
    private String haddress;
    private String uid;

	public Hx() {
	}

	public Hx(String hname, String hobject, Date hbtime, Date hetime, int hmoney, String haddress, String uid) {
		this.hname = hname;
		this.hobject = hobject;
		this.hbtime = hbtime;
		this.hetime = hetime;
		this.hmoney = hmoney;
		this.haddress = haddress;
		this.uid = uid;
	}

	public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHobject() {
        return hobject;
    }

    public void setHobject(String hobject) {
        this.hobject = hobject;
    }

    public Date getHbtime() {
        return hbtime;
    }

    public void setHbtime(Date hbtime) {
        this.hbtime = hbtime;
    }

    public Date getHetime() {
        return hetime;
    }

    public void setHetime(Date hetime) {
        this.hetime = hetime;
    }

    public int getHmoney() {
        return hmoney;
    }

    public void setHmoney(int hmoney) {
        this.hmoney = hmoney;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    @Override
    public String toString() {
        return "Hx [hid=" + hid + ", hname=" + hname + ", hobject=" + hobject + ", hbtime=" + hbtime + ", hetime="
                + hetime + ", hmoney=" + hmoney + ", haddress=" + haddress + ", uid=" + uid + "]";
    }

}
