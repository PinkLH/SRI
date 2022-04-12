package net.cqwu.SRI.service;

import net.cqwu.SRI.entity.Users;

public interface UserLoginService {
    /**
     * 登录判断
     */
    Users login(String number, String upwd, String utype);

    /**
     * 修改用户信息
     */
    boolean updateUserInfo(Users user);

    /**
     * 查询用户各个科研信息的数量
     */
    int[] selectAchievementCount(String uid, String utype, int year);
    int[] selectLxCount(String uid, String utype, int year);
    int[] selectHxCount(String uid, String utype, int year);
    int[] selectWorkCount(String uid, String utype, int year);
    int[] selectPatentCount(String uid, String utype, int year);
    int[] selectThesisCount(String uid, String utype, int year);
}
