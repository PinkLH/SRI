package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Awork;

import java.text.SimpleDateFormat;

public class AworkDateFormatExpansion extends Awork {

    private String awname;
    private String awtimeChanged;
    private String awpress;
    private String awperson;

    @Override
    public String getAwname() {
        return awname;
    }

    @Override
    public void setAwname(String awname) {
        this.awname = awname;
    }

    @Override
    public String getAwperson() {
        return awperson;
    }

    @Override
    public void setAwperson(String awperson) {
        this.awperson = awperson;
    }

    @Override
    public String getAwpress() {
        return awpress;
    }

    @Override
    public void setAwpress(String awpress) {
        this.awpress = awpress;
    }

    public String getAwtimeChanged() {
        if (getAwtime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            awtimeChanged = sdf.format(getAwtime());
        }
        return awtimeChanged;
    }

    public void setAwtimeChanged(String awtimeChanged) {
        this.awtimeChanged = awtimeChanged;
    }

}
