package net.cqwu.SRI.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.cqwu.SRI.entity.Achievement;

public interface UserAchievementMapper {
    /**
     * 查询所有的成果获奖
     */
    @Select("select * from achievement")
    List<Achievement> selectAllAchievement();

    /**
     * 查询所有成果获奖的Excel数据
     */
    List<Achievement> selectExcelAchievement();

    /**
     * 查询某个用户的所有成果获奖的Excel数据
     */
    List<Achievement> selectAllUserExcelAchievement(String uid);

    /**
     * 查询某个用户的所有成果获奖
     */
    @Select("select * from achievement where uid = #{param1}")
    List<Achievement> selectAllUserAchievement(String uid);

    /**
     * 查询某个成果获奖
     */
    @Select("select * from achievement where aid = ${param1}")
    Achievement selectAchievementByAid(int aid);

    /**
     * 删除某个成果获奖
     */
    @Delete("delete from achievement where aid = ${param1}")
    boolean DeleteAchievement(int aid);

    /**
     * 增加一个成果获奖
     */
    @Insert("insert into achievement(aname,aunit,atime,alevel,atype,aaddress,uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6},#{param7})")
    boolean AddAchievement(String aname, String aunit, Date atime, String alevel, String atype, String aaddress, String uid);

    /**
     * 条件查询成果获奖
     */
    List<Achievement> selectAchievementCondition(@Param("aname") String aname, @Param("alevel") String alevel,
                                        @Param("atype") String atype, @Param("uid") String uid, @Param("utype") String utype);

    /**
     * 修改某个成果获奖
     */
    boolean UpdateAchievement(String aname, String aunit, Date atime, String alevel, String atype,
                              String aaddress, int aid);

}
