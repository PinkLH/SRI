package net.cqwu.SRI.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.cqwu.SRI.entity.Users;

public interface UserLoginMapper {
	/**
	 * 查询是否有某个工号的用户
	 */
	@Select("select * from users where uid = #{param1} and upwd = #{param2} and utype = #{param3}")
	Users idLogin(String number, String upwd, String utype);

	/**
	 * 查询是否有某个电话的用户
	 */
	@Select("select * from users where utel = #{param1} and upwd = #{param2} and utype = #{param3}")
	Users telLogin(String number, String upwd, String utype);

	/**
	 * 修改用户信息
	 */
	boolean updateUserInfo(@Param("uid") String uid, @Param("utel") String utel, @Param("upwd") String upwd);

	/**
	 * 查询用户各个科研信息的数量
	 */
	Integer selectAchievementCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectLxCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectHxCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectSworkCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectAworkCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectPatentCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
	Integer selectThesisCount(@Param("uid") String uid, @Param("utype") String utype, @Param("year") int year);
}
