package net.cqwu.SRI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.mapper.UserLoginMapper;
import net.cqwu.SRI.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    /**
     * 数据访问层接口
     */
    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * 登录判断
     */
    @Override
    public Users login(String number, String upwd, String utype) {
        Users user = userLoginMapper.idLogin(number, upwd, utype);
        if (user != null) {
            return user;
        } else {
            user = userLoginMapper.telLogin(number, upwd, utype);
            return user;
        }
    }

    /**
     * 修改用户信息
     */
    @Override
    public boolean updateUserInfo(Users user) {
        return userLoginMapper.updateUserInfo(user.getUid(), user.getUtel(), user.getUpwd());
    }

    /**
     * 查询用户各个科研信息的数量
     */
    @Override
    public int[] selectAchievementCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectAchievementCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }

    @Override
    public int[] selectLxCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectLxCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }

    @Override
    public int[] selectHxCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectHxCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }

    @Override
    public int[] selectWorkCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectSworkCount(uid, utype, year) + userLoginMapper.selectAworkCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }

    @Override
    public int[] selectPatentCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectPatentCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }

    @Override
    public int[] selectThesisCount(String uid, String utype, int year) {
        int[] num = new int[5];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = userLoginMapper.selectThesisCount(uid, utype, year);
            year -= 1;
        }
        return num;
    }
}
