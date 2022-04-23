package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Thesis;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThesisDateFormatExpansion extends Thesis {
	private String tname;
	private String ttimeChanged;
	private String ttype;

	@Override
	public String getTname() {
		return tname;
	}

	@Override
	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTtimeChanged() {
		if (getTtime() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ttimeChanged = sdf.format(getTtime());
		}
		return ttimeChanged;
	}

	public void setTtimeChanged(String ttimeChanged) {
		this.ttimeChanged = ttimeChanged;
	}

	@Override
	public String getTtype() {
		return ttype;
	}

	@Override
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

}
