package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Lp;
import net.cqwu.SRI.entity.Lx;

import java.util.List;

public interface UserLxService {
    /**
     * 查询所有的立项
     */
    List<Lx> selectLx();

    /**
     * 查询某个用户的所有立项
     */
    List<Lx> selectLx(String uid);

    /**
     * 查询某个立项的所有成员
     */
    List<Lp> selectLp(String lid);

    /**
     * 条件查询立项
     */
    List<Lx> selectLx(Lx lx, String uid);

    /**
     * 查询某个成果获奖
     */
    Lx selectLxByLid(String lid);

    /**
     * 增加立项
     */
    boolean AddLx(Lx lx);

    /**
     * 增加立项成员
     */
    boolean AddLp(List<Lp> lps);

    /**
     * 删除立项
     */
    boolean DeleteLx(String lid);

    /**
     * 删除立项成员
     */
    boolean DeleteLp(String lid);

    /**
     * 修改立项成员
     */
    boolean UpdateLp(List<Lp> lps, String oldLid);

    /**
     * 修改某个立项
     */
    boolean UpdateLx(Lx lx, String oldLid);

    /**
     * 查询所有立项的Excel数据
     */
    List<Lx> selectExcelLx();

    /**
     * 查询某个用户的所有立项的Excel数据
     */
    List<Lx> selectExcelLx(String uid);
}
