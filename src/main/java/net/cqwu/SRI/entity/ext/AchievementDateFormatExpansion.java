package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Achievement;

import java.text.SimpleDateFormat;

public class AchievementDateFormatExpansion extends Achievement {

    private String aname;
    private String aunit;
    private String atimeChanged;
    private String alevel;
    private String atype;

    @Override
    public String getAname() {
        return aname;
    }

    @Override
    public void setAname(String aname) {
        this.aname = aname;
    }

    @Override
    public String getAunit() {
        return aunit;
    }

    @Override
    public void setAunit(String aunit) {
        this.aunit = aunit;
    }

    @Override
    public String getAlevel() {
        return alevel;
    }

    @Override
    public void setAlevel(String alevel) {
        this.alevel = alevel;
    }

    @Override
    public String getAtype() {
        return atype;
    }

    @Override
    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getAtimeChanged() {
        if (getAtime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            atimeChanged = sdf.format(getAtime());
        }
        return atimeChanged;
    }

    public void setAtimeChanged(String atimeChanged) {
        this.atimeChanged = atimeChanged;
    }
}
