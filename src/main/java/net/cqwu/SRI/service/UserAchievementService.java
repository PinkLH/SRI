package net.cqwu.SRI.service;

import java.util.List;

import net.cqwu.SRI.entity.Achievement;

public interface UserAchievementService {
    /**
     * 查询所有的成果获奖
     */
    List<Achievement> selectAchievement();

    /**
     * 查询某个用户的所有成果获奖的Excel数据
     */
    List<Achievement> selectExcelAchievement();

    /**
     * 查询某个用户的所有成果获奖
     */
    List<Achievement> selectExcelAchievement(String uid);

    /**
     * 条件查询成果获奖
     */
    List<Achievement> selectAchievement(Achievement achievement, String utype);

    /**
     * 查询某个用户的所有成果获奖
     */
    List<Achievement> selectAchievement(String uid);

    /**
     * 查询某个成果获奖
     */
    Achievement selectAchievement(int aid);

    /**
     * 删除某个成果获奖
     */
    boolean DeleteAchievement(int aid);

    /**
     * 增加一个成果获奖
     */
    boolean AddAchievement(Achievement achievement);

    /**
     * 修改某个成果获奖
     */
    boolean UpdateAchievement(Achievement achievement);

}
