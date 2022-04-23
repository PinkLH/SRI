package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Swork;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SworkDateFormatExpansion extends Swork {

    private String swname;
    private String swid;
    private String swperson;

    @Override
    public String getSwid() {
        return swid;
    }

    @Override
    public void setSwid(String swid) {
        this.swid = swid;
    }

    @Override
    public String getSwname() {
        return swname;
    }

    @Override
    public void setSwname(String swname) {
        this.swname = swname;
    }

    @Override
    public String getSwperson() {
        return swperson;
    }

    @Override
    public void setSwperson(String swperson) {
        this.swperson = swperson;
    }

}
