package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Awork;

import java.util.List;

public interface UserAworkService {

    /**
     * 查询所有的学术著作
     */
    List<Awork> selectAwork();

    /**
     * 查询某个用户的所有学术著作
     */
    List<Awork> selectAwork(String uid);

    /**
     * 查询某个学术著作
     */
    Awork selectAwork(int awid);

    /**
     * 增加一个学术著作
     */
    boolean AddAwork(Awork awork);

    /**
     * 删除某个学术著作
     */
    boolean DeleteAwork(int awid);

    /**
     * 修改学术著作
     */
    boolean UpdateAwork(Awork awork);

    /**
     * 通过名称查询学术著作
     */
    List<Awork> selectAwork(String wname, String utype, String uid);
}
