package net.cqwu.SRI.entity;

import java.io.Serializable;

public class Users implements Serializable {
	private String uid;
	private String upwd;
	private String uname;
	private String utel;
	private String utype;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	@Override
	public String toString() {
		return "users [uid=" + uid + ", upwd=" + upwd + ", uname=" + uname + ", utel=" + utel + ", utype=" + utype
				+ "]";
	}
}
