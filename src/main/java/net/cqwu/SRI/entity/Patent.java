package net.cqwu.SRI.entity;

import java.io.Serializable;
import java.util.Date;

public class Patent implements Serializable {
	private String pid;
	private String pname;
	private String pstate;
	private Date ptime;
	private String ppatentee;
	private String paddress;
	private String uid;

	public Patent() {
	}

	public Patent(String pid, String pname, String pstate, Date ptime, String ppatentee, String paddress, String uid) {
		this.pid = pid;
		this.pname = pname;
		this.pstate = pstate;
		this.ptime = ptime;
		this.ppatentee = ppatentee;
		this.paddress = paddress;
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public String getPpatentee() {
		return ppatentee;
	}

	public void setPpatentee(String ppatentee) {
		this.ppatentee = ppatentee;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Patent{" +
				"pid='" + pid + '\'' +
				", pname='" + pname + '\'' +
				", pstate='" + pstate + '\'' +
				", ptime=" + ptime +
				", ppatentee='" + ppatentee + '\'' +
				", paddress='" + paddress + '\'' +
				", uid='" + uid + '\'' +
				'}';
	}
}
