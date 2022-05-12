package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Swork;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserSworkMapper {

    /**
     * 松紧度所有的软件著作
     */
    @Select("select * from swork")
    List<Swork> selectAllSwork();

    /**
     * 查询某个用户的所有软件著作
     */
    @Select("select * from swork where uid = #{param1}")
    List<Swork> selectAllUserSwork(String uid);

    /**
     * 增加一个软件著作
     */
    @Insert("insert into swork(swid, swname, swperson, swtime, swaddress, uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6})")
    boolean AddSwork(String swid, String swname, String swperson, Date swtime, String swaddress, String uid);

    /**
     * 查询某个软件著作
     */
    @Select("select * from swork where swid = #{param1}")
    Swork selectSworkBySwid(String swid);

    /**
     * 删除某个软件著作
     */
    @Delete("delete from swork where swid = #{param1}")
    boolean DeleteSwork(String swid);

    /**
     * 通过名称搜索软件著作
     */
    List<Swork> selectSworkCondition(@Param("wname") String wname, @Param("utype") String utype, @Param("uid") String uid);

    /**
     * 修改软件著作
     */
    boolean UpdateSwork(String swid, String swname, String swperson, String swaddress, String oldswid);

    /**
     * 查询Excel导出的数据
     */
    List<Swork> selectExcelSwork();

    /**
     * 查询某个用户导出的Excel数据
     */
    List<Swork> selectAllUserExcelSwork(String uid);
}
