package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Patent;

import java.text.SimpleDateFormat;

public class PatentDateFormatExpansion extends Patent {
	private String pid;
	private String pname;
	private String ptimeChanged;
	private String pstate;
	private String ppatentee;

	@Override
	public String getPid() {
		return pid;
	}

	@Override
	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String getPname() {
		return pname;
	}

	@Override
	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String getPstate() {
		return pstate;
	}

	@Override
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	public String getPtimeChanged() {
		if (getPtime() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ptimeChanged = sdf.format(getPtime());
		}
		return ptimeChanged;
	}

	public void setPtimeChanged(String ptimeChanged) {
		this.ptimeChanged = ptimeChanged;
	}

	@Override
	public String getPpatentee() {
		return ppatentee;
	}

	@Override
	public void setPpatentee(String ppatentee) {
		this.ppatentee = ppatentee;
	}

}
