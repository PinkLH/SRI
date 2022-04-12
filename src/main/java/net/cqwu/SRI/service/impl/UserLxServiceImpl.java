package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Lp;
import net.cqwu.SRI.entity.Lx;
import net.cqwu.SRI.mapper.UserLxMapper;
import net.cqwu.SRI.service.UserLxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLxServiceImpl implements UserLxService {
    /**
     * 数据访问层接口
     */
    @Autowired
    private UserLxMapper userLxMapper;

    /**
     * 查询所有的立项
     */
    @Override
    public List<Lx> selectLx() {
        return userLxMapper.selectAllLx();
    }

    /**
     * 查询某个用户的所有立项
     */
    @Override
    public List<Lx> selectLx(String uid) {
        return userLxMapper.selectAllUserLx(uid);
    }

    /**
     * 查询某个立项的所有成员
     */
    @Override
    public List<Lp> selectLp(String lid) {
        return userLxMapper.selectLp(lid);
    }

    /**
     * 条件查询立项
     */
    @Override
    public List<Lx> selectLx(Lx lx, String utype) {
        return userLxMapper.selectLxCondition(
                lx.getLid(),
                lx.getLname(),
                lx.getLtype(),
                lx.getUid(),
                utype
        );
    }

    /**
     * 查询某个成果获奖
     */
    @Override
    public Lx selectLxByLid(String lid) {
        return userLxMapper.selectLxByLid(lid);
    }

    /**
     * 增加立项
     */
    @Override
    public boolean AddLx(Lx lx) {
        return userLxMapper.addLx(
                lx.getLid(),
                lx.getLname(),
                lx.getLtype(),
                lx.getLbtime(),
                lx.getLetime(),
                lx.getLmoney(),
                lx.getLaddress(),
                lx.getLperson(),
                lx.getUid()
        );
    }

    /**
     * 增加立项成员
     */
    @Override
    public boolean AddLp(List<Lp> lps) {
        for (Lp lp: lps) {
            if (!userLxMapper.AddLp(lp.getLpid(), lp.getLpname(), lp.getLid())){
                return false;
            }
        }
        return true;
    }

    /**
     * 删除立项
     */
    @Override
    public boolean DeleteLx(String lid) {
        return userLxMapper.DeleteLx(lid);
    }

    /**
     * 删除立项成员
     */
    @Override
    public boolean DeleteLp(String lid) {
        return userLxMapper.DeleteLp(lid);
    }

    /**
     * 修改立项成员
     */
    @Override
    public boolean UpdateLp(List<Lp> lps, String oldLid) {
        if (userLxMapper.DeleteLp(oldLid)){
            return this.AddLp(lps);
        }else {
            if (selectLp(oldLid).isEmpty()){
                return this.AddLp(lps);
            }else {
                return false;
            }
        }
    }

    /**
     * 修改某个立项
     */
    @Override
    public boolean UpdateLx(Lx lx, String oldLid) {
        return userLxMapper.UpdateLx(
                lx.getLid(),
                lx.getLname(),
                lx.getLtype(),
                lx.getLbtime(),
                lx.getLetime(),
                lx.getLmoney(),
                lx.getLaddress(),
                lx.getLperson(),
                oldLid
        );
    }
}
