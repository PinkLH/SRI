package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Lx;
import java.text.SimpleDateFormat;

public class LxDateFormatExpansion extends Lx {
    private String lid;
    private String lname;
    private String ltype;
    private String lperson;
    private String lbtimeChanged;
    private String letimeChanged;
    private int lmoney;

    @Override
    public String getLid() {
        return lid;
    }

    @Override
    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String getLtype() {
        return ltype;
    }

    @Override
    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    public String getLbtimeChanged() {
        if (getLbtime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            lbtimeChanged = sdf.format(getLbtime());
        }
        return lbtimeChanged;
    }

    public void setLbtimeChanged(String lbtimeChanged) {
        this.lbtimeChanged = lbtimeChanged;
    }

    public String getLetimeChanged() {
        if (getLetime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            letimeChanged = sdf.format(getLetime());
        }
        return letimeChanged;
    }

    public void setLetimeChanged(String letimeChanged) {
        this.letimeChanged = letimeChanged;
    }

    @Override
    public int getLmoney() {
        return lmoney;
    }

    @Override
    public void setLmoney(int lmoney) {
        this.lmoney = lmoney;
    }

    @Override
    public String getLperson() {
        return lperson;
    }

    @Override
    public void setLperson(String lperson) {
        this.lperson = lperson;
    }

}
