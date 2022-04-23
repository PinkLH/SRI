package net.cqwu.SRI.entity.ext;

import net.cqwu.SRI.entity.Hx;

import java.text.SimpleDateFormat;

public class HxDateFormatExpansion extends Hx {

    private String hname;
    private String hobject;
    private String hbtimeChanged;
    private String hetimeChanged;
    private int hmoney;

    @Override
    public String getHname() {
        return hname;
    }

    @Override
    public void setHname(String hname) {
        this.hname = hname;
    }

    @Override
    public String getHobject() {
        return hobject;
    }

    @Override
    public void setHobject(String hobject) {
        this.hobject = hobject;
    }

    public String getHbtimeChanged() {
        if (getHbtime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            hbtimeChanged = sdf.format(getHbtime());
        }
        return hbtimeChanged;
    }

    public void setHbtimeChanged(String hbtimeChanged) {
        this.hbtimeChanged = hbtimeChanged;
    }

    public String getHetimeChanged() {
        if(getHetime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            hetimeChanged = sdf.format(getHetime());
        }
        return hetimeChanged;
    }

    public void setHetimeChanged(String hetimeChanged) {
        this.hetimeChanged = hetimeChanged;
    }

    @Override
    public int getHmoney() {
        return hmoney;
    }

    @Override
    public void setHmoney(int hmoney) {
        this.hmoney = hmoney;
    }

}
