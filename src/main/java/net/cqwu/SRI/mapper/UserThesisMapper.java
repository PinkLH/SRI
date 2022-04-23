package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Thesis;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserThesisMapper {

    /**
     * 查询所有的成果获奖
     */
    @Select("select * from thesis")
    List<Thesis> selectAllThesis();

    /**
     * 查询某个用户的所有成果获奖
     */
    @Select("select * from thesis where uid = #{param1}")
    List<Thesis> selectAllUserThesis(String uid);

    /**
     * 查询某个成果获奖
     */
    @Select("select * from thesis where tid = ${param1}")
    Thesis selectThesisByAid(int tid);

    /**
     * 删除某个成果获奖
     */
    @Delete("delete from thesis where tid = ${param1}")
    boolean DeleteThesis(int tid);

    /**
     * 增加一个成果获奖
     */
    @Insert("insert into thesis(tname, tperiodical, ttime, ttype, taddress, uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6})")
    boolean AddThesis(String tname, String tperiodical, Date ttime, String ttype, String taddress, String uid);

    /**
     * 条件查询成果获奖
     */
    List<Thesis> selectThesisCondition(@Param("tname") String tname, @Param("ttype") String ttype,
                                       @Param("uid") String uid, @Param("utype") String utype);

    /**
     * 修改某个成果获奖
     */
    boolean UpdateThesis(String tname, String tperiodical, Date ttime, String ttype, String taddress, int tid);


    /**
     * 查询所有论文的Excel数据
     */
    List<Thesis> selectExcelThesis();

    /**
     * 查询某个用户的所有论文的Excel数据
     * @param uid 用户ID
     */
    List<Thesis> selectAllUserExcelThesis(String uid);
}
