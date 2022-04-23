package net.cqwu.SRI.entity;

import java.io.Serializable;
import java.util.Date;

public class Achievement implements Serializable {
	private int aid;
	private String aname;
	private String aunit;
	private Date atime;
	private String alevel;
	private String atype;
	private String aaddress;
	private String uid;

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAunit() {
		return aunit;
	}
	public void setAunit(String aunit) {
		this.aunit = aunit;
	}
	public Date getAtime() {
		return atime;
	}
	public void setAtime(Date atime) {
		this.atime = atime;
	}
	public String getAlevel() {
		return alevel;
	}
	public void setAlevel(String alevel) {
		this.alevel = alevel;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getAaddress() {
		return aaddress;
	}
	public void setAaddress(String aaddress) {
		this.aaddress = aaddress;
	}
	@Override
	public String toString() {
		return "Achievement [aid=" + aid + ", aname=" + aname + ", aunit=" + aunit + ", atime=" + atime + ", alevel="
				+ alevel + ", atype=" + atype + ", aaddress=" + aaddress + ", uid=" + uid + "]";
	}
	
	
}
