package net.cqwu.SRI.entity;

import java.io.Serializable;

public class Lp implements Serializable {
	private String lpid;
	private String lpname;
	private String lid;

	public Lp() {
	}

	public Lp(String lpid, String lpname, String lid) {
		this.lpid = lpid;
		this.lpname = lpname;
		this.lid = lid;
	}

	public String getLpid() {
		return lpid;
	}

	public void setLpid(String lpid) {
		this.lpid = lpid;
	}

	public String getLpname() {
		return lpname;
	}

	public void setLpname(String lpname) {
		this.lpname = lpname;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	@Override
	public String toString() {
		return "Lp{" +
				"lpid='" + lpid + '\'' +
				", lpname='" + lpname + '\'' +
				", lid='" + lid + '\'' +
				'}';
	}
}
