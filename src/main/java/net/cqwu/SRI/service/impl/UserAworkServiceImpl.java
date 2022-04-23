package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Awork;
import net.cqwu.SRI.mapper.UserAworkMapper;
import net.cqwu.SRI.service.UserAworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAworkServiceImpl implements UserAworkService {

    /**
     * 数据层接口
     */
    @Autowired
    private UserAworkMapper userAworkMapper;

    /**
     * 查询所有学术著作
     */
    @Override
    public List<Awork> selectAwork() {
        return userAworkMapper.selectAllAwork();
    }

    /**
     * 查询某个用户的所有的学术著作
     */
    @Override
    public List<Awork> selectAwork(String uid) {
        return userAworkMapper.selectAllUserAwork(uid);
    }

    /**
     * 查询某个学术著作
     */
    @Override
    public Awork selectAwork(int awid) {
        return userAworkMapper.selectAworkByAwid(awid);
    }

    /**
     * 增加一个学术著作
     */
    @Override
    public boolean AddAwork(Awork awork) {
        return userAworkMapper.AddAwork(
                awork.getAwname(),
                awork.getAwperson(),
                awork.getAwpress(),
                awork.getAwtime(),
                awork.getAwaddress(),
                awork.getUid()
        );
    }

    /**
     * 删除某个学术著作
     */
    @Override
    public boolean DeleteAwork(int awid) {
        return userAworkMapper.DeleteAwork(awid);
    }

    /**
     * 修改学术著作
     */
    @Override
    public boolean UpdateAwork(Awork awork) {
        return userAworkMapper.UpdateAwork(
                awork.getAwname(),
                awork.getAwperson(),
                awork.getAwpress(),
                awork.getAwtime(),
                awork.getAwaddress(),
                awork.getAwid()
        );
    }

    /**
     * 通过名称查询学术著作
     */
    @Override
    public List<Awork> selectAwork(String wname, String utype, String uid) {
        return userAworkMapper.selectAworkCondition(wname, utype, uid);
    }

    /**
     * 查询所有学术著作的Excel数据
     */
    @Override
    public List<Awork> selectExcelAwork() {
        return userAworkMapper.selectExcelAwork();
    }

    /**
     * 查询某个用户的所有学术著作的Excel数据
     * @param uid 用户ID
     */
    @Override
    public List<Awork> selectExcelAwork(String uid) {
        return userAworkMapper.selectAllUserExcelAwork(uid);
    }

}
