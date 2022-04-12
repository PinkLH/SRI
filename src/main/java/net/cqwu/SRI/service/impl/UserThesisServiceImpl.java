package net.cqwu.SRI.service.impl;

import net.cqwu.SRI.entity.Thesis;
import net.cqwu.SRI.mapper.UserThesisMapper;
import net.cqwu.SRI.service.UserThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserThesisServiceImpl implements UserThesisService {

    /**
     * 数据访问层接口
     */
    @Autowired
    private UserThesisMapper userThesisMapper;

    /**
     * 查询所有的论文
     */
    @Override
    public List<Thesis> selectThesis() {
        return userThesisMapper.selectAllThesis();
    }

    /**
     * 条件查询论文
     */
    @Override
    public List<Thesis> selectThesis(Thesis thesis, String utype) {
        return userThesisMapper.selectThesisCondition(
                thesis.getTname(),
                thesis.getTtype(),
                thesis.getUid(),
                utype
        );
    }

    /**
     * 查询某个用户的所有论文
     */
    @Override
    public List<Thesis> selectThesis(String uid) {
        return userThesisMapper.selectAllUserThesis(uid);
    }

    /**
     * 查询某个论文
     */
    @Override
    public Thesis selectThesis(int tid) {
        return userThesisMapper.selectThesisByAid(tid);
    }

    /**
     * 删除某个论文
     */
    @Override
    public boolean DeleteThesis(int tid) {
        return userThesisMapper.DeleteThesis(tid);
    }

    /**
     * 增加一个论文
     */
    @Override
    public boolean AddThesis(Thesis thesis) {
        return userThesisMapper.AddThesis(
                thesis.getTname(),
                thesis.getTperiodical(),
                thesis.getTtime(),
                thesis.getTtype(),
                thesis.getTaddress(),
                thesis.getUid()
        );
    }

    /**
     * 修改某个论文
     */
    @Override
    public boolean UpdateThesis(Thesis thesis) {
        return userThesisMapper.UpdateThesis(
                thesis.getTname(),
                thesis.getTperiodical(),
                thesis.getTtime(),
                thesis.getTtype(),
                thesis.getTaddress(),
                thesis.getTid()
        );
    }

}
