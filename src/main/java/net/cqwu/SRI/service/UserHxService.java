package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Hx;

import java.util.List;

public interface UserHxService {

    /**
     * 查询所有的横项
     */
    List<Hx> selectHx();

    /**
     * 查询某个用户的所有横项
     */
    List<Hx> selectHx(String uid);

    /**
     * 条件查询横项
     */
    List<Hx> selectHx(Hx Hx, String uid);

    /**
     * 查询某个成果获奖
     */
    Hx selectHx(int hid);

    /**
     * 增加横项
     */
    boolean AddHx(Hx Hx);

    /**
     * 删除横项
     */
    boolean DeleteHx(int hid);

    /**
     * 修改某个横项
     */
    boolean UpdateHx(Hx Hx);

    /**
     * 查询某个用户的所有横项的Excel数据
     */
    List<Hx> selectExcelHx();

    /**
     * 查询某个用户的所有横项
     * @param uid 用户ID
     */
    List<Hx> selectExcelHx(String uid);
}
