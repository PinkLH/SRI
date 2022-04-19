package net.cqwu.SRI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cqwu.SRI.entity.Achievement;
import net.cqwu.SRI.mapper.UserAchievementMapper;
import net.cqwu.SRI.service.UserAchievementService;

@Service
public class UserAchievementServiceImpl implements UserAchievementService {
    /**
     * 数据访问层接口
     */
    @Autowired
    private UserAchievementMapper userAchievementMapper;

    /**
     * 查询所有的成果获奖
     */
    @Override
    public List<Achievement> selectAchievement() {
        return userAchievementMapper.selectAllAchievement();
    }

    /**
     * 查询所有成果获奖的Excel数据
     */
    @Override
    public List<Achievement> selectExcelAchievement() {
        return userAchievementMapper.selectExcelAchievement();
    }

    /**
     * 查询某个用户的所有成果获奖的Excel数据
     */
    @Override
    public List<Achievement> selectExcelAchievement(String uid) {
        return userAchievementMapper.selectAllUserExcelAchievement(uid);
    }

    /**
     * 条件查询成果获奖
     */
    @Override
    public List<Achievement> selectAchievement(Achievement achievement, String utype) {
        return userAchievementMapper.selectAchievementCondition(
                achievement.getAname(),
                achievement.getAlevel(),
                achievement.getAtype(),
                achievement.getUid(),
                utype
        );
    }

    /**
     * 查询某个用户的所有成果获奖
     */
    @Override
    public List<Achievement> selectAchievement(String uid) {
        return userAchievementMapper.selectAllUserAchievement(uid);
    }

    /**
     * 查询某个成果获奖
     */
    @Override
    public Achievement selectAchievement(int aid) {
        return userAchievementMapper.selectAchievementByAid(aid);
    }

    /**
     * 删除某个成果获奖
     */
    @Override
    public boolean DeleteAchievement(int aid) {
        return userAchievementMapper.DeleteAchievement(aid);
    }

    /**
     * 增加一个成果获奖
     */
    @Override
    public boolean AddAchievement(Achievement achievement) {
        return userAchievementMapper.AddAchievement(
                achievement.getAname(),
                achievement.getAunit(),
                achievement.getAtime(),
                achievement.getAlevel(),
                achievement.getAtype(),
                achievement.getAaddress(),
                achievement.getUid()
        );
    }

    /**
     * 修改某个成果获奖
     */
    @Override
    public boolean UpdateAchievement(Achievement achievement) {
        return userAchievementMapper.UpdateAchievement(
                achievement.getAname(),
                achievement.getAunit(),
                achievement.getAtime(),
                achievement.getAlevel(),
                achievement.getAtype(),
                achievement.getAaddress(),
                achievement.getAid()
        );
    }

}
