package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Lp;
import net.cqwu.SRI.entity.Lx;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserLxMapper {
    /**
     * 查询所有的立项
     */
    @Select("select * from lx")
    List<Lx> selectAllLx();

    /**
     * 查询某个用户的所有立项
     */
    @Select("select * from lx where uid = #{param1}")
    List<Lx> selectAllUserLx(String uid);

    /**
     * 查询某个立项
     */
    @Select("select * from lx where lid = #{param1}")
    Lx selectLxByLid(String lid);

    /**
     * 查询某个立项的所有成员
     */
    @Select("select * from lp where lid = #{param1}")
    List<Lp> selectLp(String lid);

    /**
     * 增加立项
     */
    @Insert("insert into lx(lid, lname, ltype, lbtime, letime, lmoney, laddress, lperson, uid) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5},${param6},#{param7},#{param8},#{param9})")
    boolean addLx(String lid, String lname, String ltype, Date lbtime, Date letime, int lmoney, String laddress, String lperson, String uid);

    /**
     * 增加立项成员
     */
    @Insert("insert into lp(lpid, lpname, lid) values(#{param1},#{param2},#{param3})")
    boolean AddLp(String lpid, String lpname, String lid);

    /**
     * 删除立项
     */
    @Delete("delete from lx where lid = #{param1}")
    boolean DeleteLx(String lid);

    /**
     * 删除立项成员
     */
    @Delete("delete from lp where lid = #{param1}")
    boolean DeleteLp(String lid);

    /**
     * 条件查询立项
     */
    List<Lx> selectLxCondition(@Param("lid") String lid, @Param("lname") String lname, @Param("ltype") String ltype,
                               @Param("uid") String uid, @Param("utype") String utype);

    /**
     * 修改某个立项
     */
    boolean UpdateLx(String lid, String lname, String ltype, Date lbtime, Date letime,
                     int lmoney, String laddress, String lperson, String oldLid);
}
