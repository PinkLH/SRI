package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Pp;
import net.cqwu.SRI.entity.Patent;

import java.util.List;

public interface UserPatentService {

    /**
     * 查询所有的专利
     */
    List<Patent> selectPatent();

    /**
     * 查询某个用户的所有专利
     */
    List<Patent> selectPatent(String uid);

    /**
     * 查询某个专利的所有成员
     */
    List<Pp> selectPp(String pid);

    /**
     * 条件查询专利
     */
    List<Patent> selectPatent(Patent patent, String uid);

    /**
     * 查询某个成果获奖
     */
    Patent selectPatentByPid(String pid);

    /**
     * 增加专利
     */
    boolean AddPatent(Patent patent);

    /**
     * 增加专利成员
     */
    boolean AddPp(List<Pp> pps);

    /**
     * 删除专利
     */
    boolean DeletePatent(String pid);

    /**
     * 删除专利成员
     */
    boolean DeletePp(String pid);

    /**
     * 修改专利成员
     */
    boolean UpdatePp(List<Pp> pps, String oldPid);

    /**
     * 修改某个专利
     */
    boolean UpdatePatent(Patent patent, String oldPid);
}
