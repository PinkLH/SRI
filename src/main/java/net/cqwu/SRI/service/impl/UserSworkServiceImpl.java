package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Swork;
import net.cqwu.SRI.mapper.UserSworkMapper;
import net.cqwu.SRI.service.UserSworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSworkServiceImpl implements UserSworkService {

    /**
     * 数据层接口
     */
    @Autowired
    private UserSworkMapper userSworkMapper;

    /**
     * 查询所有的软件著作
     */
    @Override
    public List<Swork> selectSwork() {
        return userSworkMapper.selectAllSwork();
    }

    /**
     * 查询某个用户的所有的软件著作
     */
    @Override
    public List<Swork> selectSwork(String uid) {
        return userSworkMapper.selectAllUserSwork(uid);
    }

    /**
     * 通过名称搜索软件著作
     */
    @Override
    public List<Swork> selectSwork(String wname, String utype, String uid) {
        return userSworkMapper.selectSworkCondition(wname, utype, uid);
    }

    /**
     * 查询某个软件著作
     */
    @Override
    public Swork selectSworkBySwid(String swid) {
        return userSworkMapper.selectSworkBySwid(swid);
    }

    /**
     * 增加一个软件著作
     */
    @Override
    public boolean AddSwork(Swork swork) {
        return userSworkMapper.AddSwork(
                swork.getSwid(),
                swork.getSwname(),
                swork.getSwperson(),
                swork.getSwtime(),
                swork.getSwaddress(),
                swork.getUid()
        );
    }

    /**
     * 删除某个软件著作
     */
    @Override
    public boolean DeleteSwork(String swid) {
        return userSworkMapper.DeleteSwork(swid);
    }

    /**
     * 修改软件著作
     */
    @Override
    public boolean UpdateSwork(Swork swork, String oldswid) {
        return userSworkMapper.UpdateSwork(
                swork.getSwid(),
                swork.getSwname(),
                swork.getSwperson(),
                swork.getSwtime(),
                swork.getSwaddress(),
                oldswid
        );
    }
}
