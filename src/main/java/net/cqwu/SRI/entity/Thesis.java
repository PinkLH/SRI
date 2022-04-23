package net.cqwu.SRI.entity;

import java.io.Serializable;
import java.util.Date;

public class Thesis implements Serializable {
	private int tid;
	private String tname;
	private String tperiodical;
	private Date ttime;
	private String ttype;
	private String taddress;
	private String uid;

	public Thesis() {
	}

	public Thesis(String tname, String tperiodical, Date ttime, String ttype, String taddress, String uid) {
		this.tname = tname;
		this.tperiodical = tperiodical;
		this.ttime = ttime;
		this.ttype = ttype;
		this.taddress = taddress;
		this.uid = uid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTperiodical() {
		return tperiodical;
	}

	public void setTperiodical(String tperiodical) {
		this.tperiodical = tperiodical;
	}

	public Date getTtime() {
		return ttime;
	}

	public void setTtime(Date ttime) {
		this.ttime = ttime;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTaddress() {
		return taddress;
	}

	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Thesis{" +
				"tid=" + tid +
				", tname='" + tname + '\'' +
				", tperiodical='" + tperiodical + '\'' +
				", ttime=" + ttime +
				", ttype='" + ttype + '\'' +
				", taddress='" + taddress + '\'' +
				", uid='" + uid + '\'' +
				'}';
	}
}
