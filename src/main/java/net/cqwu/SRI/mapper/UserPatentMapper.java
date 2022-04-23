package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Pp;
import net.cqwu.SRI.entity.Patent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserPatentMapper {

    /**
     * 查询所有的专利
     */
    @Select("select * from patent")
    List<Patent> selectAllPatent();

    /**
     * 查询某个用户的所有专利
     */
    @Select("select * from patent where uid = #{param1}")
    List<Patent> selectAllUserPatent(String uid);

    /**
     * 查询某个专利
     */
    @Select("select * from patent where pid = #{param1}")
    Patent selectPatentByPid(String pid);

    /**
     * 查询某个专利的所有成员
     */
    @Select("select * from pp where pid = #{param1}")
    List<Pp> selectPp(String pid);

    /**
     * 增加专利
     */
    @Insert("insert into patent(pid, pname, pstate, ptime, ppatentee, paddress, uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6},#{param7})")
    boolean addPatent(String pid, String pname, String pstate, Date ptime, String ppatentee, String paddress, String uid);

    /**
     * 增加专利成员
     */
    @Insert("insert into pp(ppid, ppname, pid) values(#{param1},#{param2},#{param3})")
    boolean AddPp(String ppid, String ppname, String pid);

    /**
     * 删除专利
     */
    @Delete("delete from patent where pid = #{param1}")
    boolean DeletePatent(String pid);

    /**
     * 删除专利成员
     */
    @Delete("delete from pp where pid = #{param1}")
    boolean DeletePp(String pid);

    /**
     * 条件查询专利
     */
    List<Patent> selectPatentCondition(@Param("pid") String pid, @Param("pname") String pname,
                                       @Param("uid") String uid, @Param("utype") String utype);

    /**
     * 修改某个专利
     */
    boolean UpdatePatent(String pid, String pname, String pstate, Date ptime, String ppatentee, String paddress, String oldPid);

    /**
     * 查询所有专利的Excel数据
     */
    List<Patent> selectExcelPatent();

    /**
     * 查询某个用户的所有专利的Excel数据
     * @param uid 用户ID
     */
    List<Patent> selectAllUserExcelPatent(String uid);
}
