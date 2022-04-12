package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Hx;
import net.cqwu.SRI.mapper.UserHxMapper;
import net.cqwu.SRI.service.UserHxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHxServiceImpl implements UserHxService {
    /**
     * 数据访问层接口
     */
    @Autowired
    private UserHxMapper userHxMapper;

    /**
     * 查询所有的横项
     */
    @Override
    public List<Hx> selectHx() {
        return userHxMapper.selectAllHx();
    }

    /**
     * 条件查询横项
     */
    @Override
    public List<Hx> selectHx(Hx hx, String utype) {
        return userHxMapper.selectHxCondition(
                hx.getHname(),
                hx.getHobject(),
                hx.getUid(),
                utype
        );
    }

    /**
     * 查询某个用户的所有横项
     */
    @Override
    public List<Hx> selectHx(String uid) {
        return userHxMapper.selectAllUserHx(uid);
    }

    /**
     * 查询某个横项
     */
    @Override
    public Hx selectHx(int hid) {
        return userHxMapper.selectHxByHid(hid);
    }

    /**
     * 删除某个横项
     */
    @Override
    public boolean DeleteHx(int hid) {
        return userHxMapper.DeleteHx(hid);
    }

    /**
     * 增加一个横项
     */
    @Override
    public boolean AddHx(Hx hx) {
        return userHxMapper.AddHx(
                hx.getHname(),
                hx.getHobject(),
                hx.getHbtime(),
                hx.getHetime(),
                hx.getHmoney(),
                hx.getHaddress(),
                hx.getUid()
        );
    }

    /**
     * 修改某个横项
     */
    @Override
    public boolean UpdateHx(Hx hx) {
        return userHxMapper.UpdateHx(
                hx.getHname(),
                hx.getHobject(),
                hx.getHbtime(),
                hx.getHetime(),
                hx.getHmoney(),
                hx.getHaddress(),
                hx.getHid()
        );
    }

}
