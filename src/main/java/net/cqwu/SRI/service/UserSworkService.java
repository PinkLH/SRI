package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Swork;

import java.util.List;

public interface UserSworkService {

    /**
     * 查询所有的软件著作
     */
    List<Swork> selectSwork();

    /**
     * 查询某个用户的所有软件著作
     */
    List<Swork> selectSwork(String uid);

    /**
     * 通过名称搜索软件著作
     */
    List<Swork> selectSwork(String wname, String utype, String uid);

    /**
     * 查询某个软件著作
     */
    Swork selectSworkBySwid(String swid);

    /**
     * 增加一个软件著作
     */
    boolean AddSwork(Swork swork);

    /**
     * 删除某个软件著作
     */
    boolean DeleteSwork(String swid);

    /**
     * 修改软件著作
     */
    boolean UpdateSwork(Swork swork, String oldswid);

    /**
     * 查询Excel导出的数据
     */
    List<Swork> selectExcelSwork();

    /**
     * 查询某个用户导出的Excel数据
     */
    List<Swork> selectExcelSwork(String uid);
}
