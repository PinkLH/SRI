package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Pp;
import net.cqwu.SRI.entity.Patent;
import net.cqwu.SRI.mapper.UserPatentMapper;
import net.cqwu.SRI.service.UserPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPatentServiceImpl implements UserPatentService {

    /**
     * 数据访问层接口
     */
    @Autowired
    private UserPatentMapper userPatentMapper;

    /**
     * 查询所有的专利
     */
    @Override
    public List<Patent> selectPatent() {
        return userPatentMapper.selectAllPatent();
    }

    /**
     * 查询某个用户的所有专利
     */
    @Override
    public List<Patent> selectPatent(String uid) {
        return userPatentMapper.selectAllUserPatent(uid);
    }

    /**
     * 查询某个专利的所有成员
     */
    @Override
    public List<Pp> selectPp(String pid) {
        return userPatentMapper.selectPp(pid);
    }

    /**
     * 条件查询专利
     */
    @Override
    public List<Patent> selectPatent(Patent patent, String utype) {
        return userPatentMapper.selectPatentCondition(
                patent.getPid(),
                patent.getPname(),
                patent.getUid(),
                utype
        );
    }

    /**
     * 查询某个成果获奖
     */
    @Override
    public Patent selectPatentByPid(String pid) {
        return userPatentMapper.selectPatentByPid(pid);
    }

    /**
     * 增加专利
     */
    @Override
    public boolean AddPatent(Patent patent) {
        return userPatentMapper.addPatent(
                patent.getPid(),
                patent.getPname(),
                patent.getPstate(),
                patent.getPtime(),
                patent.getPpatentee(),
                patent.getPaddress(),
                patent.getUid()
        );
    }

    /**
     * 增加专利成员
     */
    @Override
    public boolean AddPp(List<Pp> pps) {
        for (Pp pp: pps) {
            if (!userPatentMapper.AddPp(pp.getPpid(), pp.getPpname(), pp.getPid())){
                return false;
            }
        }
        return true;
    }

    /**
     * 删除专利
     */
    @Override
    public boolean DeletePatent(String pid) {
        return userPatentMapper.DeletePatent(pid);
    }

    /**
     * 删除专利成员
     */
    @Override
    public boolean DeletePp(String pid) {
        return userPatentMapper.DeletePp(pid);
    }

    /**
     * 修改专利成员
     */
    @Override
    public boolean UpdatePp(List<Pp> pps, String oldPid) {
        if (userPatentMapper.DeletePp(oldPid)){
            return this.AddPp(pps);
        }else {
            if (selectPp(oldPid).isEmpty()){
                return this.AddPp(pps);
            }else {
                return false;
            }
        }
    }

    /**
     * 修改某个专利
     */
    @Override
    public boolean UpdatePatent(Patent patent, String oldPid) {
        return userPatentMapper.UpdatePatent(
                patent.getPid(),
                patent.getPname(),
                patent.getPstate(),
                patent.getPtime(),
                patent.getPpatentee(),
                patent.getPaddress(),
                oldPid
        );
    }

    /**
     * 查询所有专利的Excel数据
     */
    @Override
    public List<Patent> selectExcelPatent() {
        return userPatentMapper.selectExcelPatent();
    }

    /**
     * 查询某个用户的所有专利的Excel数据
     *
     * @param uid 用户ID
     */
    @Override
    public List<Patent> selectExcelPatent(String uid) {
        return userPatentMapper.selectAllUserExcelPatent(uid);
    }
}
