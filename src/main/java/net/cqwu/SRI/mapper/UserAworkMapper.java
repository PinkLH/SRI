package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Awork;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface UserAworkMapper {

    /**
     * 查询所有的学术著作
     */
    @Select("select * from awork")
    List<Awork> selectAllAwork();

    /**
     * 查询某个用户的所有学术著作
     */
    @Select("select * from awork where uid = #{param1}")
    List<Awork> selectAllUserAwork(String uid);

    /**
     * 查询某个学术著作
     */
    @Select("select * from awork where awid = ${param1}")
    Awork selectAworkByAwid(int awid);

    /**
     * 增加一个学术著作
     */
    @Insert("insert into awork(awname, awperson, awpress, awtime, awaddress, uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6})")
    boolean AddAwork(String awname, String awperson, String awpress, Date awtime, String awaddress, String uid);

    /**
     * 删除某个学术著作
     */
    @Delete("delete from awork where awid = ${param1}")
    boolean DeleteAwork(int awid);

    /**
     * 条件查询学术著作
     */
    List<Awork> selectAworkCondition(@Param("awname") String awname, @Param("utype") String utype, @Param("uid") String uid);

    /**
     * 修改学术著作
     */
    boolean UpdateAwork(String awname, String awperson, String awpress, Date awtime, String awaddress, int awid);

    /**
     * 查询所有学术著作的Excel数据
     */
    List<Awork> selectExcelAwork();

    /**
     * 查询某个用户的所有学术著作的Excel数据
     */
    List<Awork> selectAllUserExcelAwork(String uid);
}
