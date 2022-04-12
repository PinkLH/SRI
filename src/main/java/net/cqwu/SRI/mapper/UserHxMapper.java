package net.cqwu.SRI.mapper;

import net.cqwu.SRI.entity.Hx;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserHxMapper {

    /**
     * 查询所有的横项
     */
    @Select("select * from hx")
    List<Hx> selectAllHx();

    /**
     * 查询某个用户的所有横项
     */
    @Select("select * from hx where uid = #{param1}")
    List<Hx> selectAllUserHx(String uid);

    /**
     * 查询某个横项
     */
    @Select("select * from hx where hid = ${param1}")
    Hx selectHxByHid(int hid);

    /**
     * 删除某个横项
     */
    @Delete("delete from hx where hid = ${param1}")
    boolean DeleteHx(int hid);

    /**
     * 增加一个横项
     */
    @Insert("insert into hx(hname, hobject, hbtime, hetime, hmoney, haddress, uid) " +
            "VALUES(#{param1},#{param2},#{param3},#{param4},${param5},#{param6},#{param7})")
    boolean AddHx(String hname, String hobject, Date hbtime, Date hetime,
                  int hmoney, String haddress, String uid);

    /**
     * 条件查询横项
     */
    List<Hx> selectHxCondition(@Param("hname") String hname, @Param("hobject") String hobject,
                               @Param("uid") String uid, @Param("utype") String utype);

    /**
     * 修改某个横项
     */
    boolean UpdateHx(String hname, String hobject, Date hbtime, Date hetime,
                     int hmoney, String haddress, int hid);

}
