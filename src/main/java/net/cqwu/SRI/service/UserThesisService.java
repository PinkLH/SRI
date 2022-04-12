package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Thesis;

import java.util.List;

public interface UserThesisService {

    /**
     * 查询所有的论文
     */
    List<Thesis> selectThesis();

    /**
     * 条件查询论文
     */
    List<Thesis> selectThesis(Thesis thesis, String utype);

    /**
     * 查询某个用户的所有论文
     */
    List<Thesis> selectThesis(String uid);

    /**
     * 查询某个论文
     */
    Thesis selectThesis(int tid);

    /**
     * 删除某个论文
     */
    boolean DeleteThesis(int tid);

    /**
     * 增加一个论文
     */
    boolean AddThesis(Thesis thesis);

    /**
     * 修改某个论文
     */
    boolean UpdateThesis(Thesis thesis);
}
