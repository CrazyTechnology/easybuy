package com.easybuy.ming.manage.user.service.impl;

import com.easybuy.ming.manage.user.service.UserService;
import com.easybuy.ming.mapper.TbUserMapper;
import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.pojo.TbUserExample;
import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ming on 2016/11/20.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;
    /**
     * @return 返回用户列表
     * @param datatablesPager
     */
    public DatatablesPager selectUserList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        List<TbUser> userList = userMapper.selectByExample(new TbUserExample());
        return PageHelper.endPage(userList);
    }

    //根据用户id查询用户信息
    public TbUser selectUserDetail(String id) {
        TbUser tbUser = userMapper.selectByPrimaryKey(Long.parseLong(id));
        return tbUser;
    }

    //新增会员
    public Integer addUser(TbUser user) {
        Integer reslut=userMapper.insertSelective(user);
        return reslut;
    }

    //删除用户
    public Integer deleteUser(String id) {
        Integer result = userMapper.deleteByPrimaryKey(Long.parseLong(id));
        return result;
    }
}
