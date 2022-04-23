package net.cqwu.SRI.entity;

import java.io.Serializable;

public class Pp implements Serializable {
	private String ppid;
	private String ppname;
	private String pid;

	public Pp() {
	}

	public Pp(String ppid, String ppname, String pid) {
		this.ppid = ppid;
		this.ppname = ppname;
		this.pid = pid;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public String getPpname() {
		return ppname;
	}

	public void setPpname(String ppname) {
		this.ppname = ppname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Pp{" +
				"ppid='" + ppid + '\'' +
				", ppname='" + ppname + '\'' +
				", pid='" + pid + '\'' +
				'}';
	}
}
