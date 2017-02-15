package com.easybuy.ming.manage.system.service.impl;

import com.easybuy.ming.mapper.TbDeptUserMapper;
import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.pojo.TbDeptUserExample;
import com.easybuy.ming.pojo.TbDeptUserExample.Criteria;
import com.easybuy.ming.manage.system.service.EmployeeService;
import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/12/4. 部门员工的业务操作
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private TbDeptUserMapper deptUserMapper;

    //根据用户名查询用户信息
    public TbDeptUser findUserByName(String username) {
        TbDeptUserExample deptUserExample=new TbDeptUserExample();
        Criteria criteria = deptUserExample.createCriteria();
        criteria.andLoginNameEqualTo(username);
        List<TbDeptUser> tbDeptUsers = deptUserMapper.selectByExample(deptUserExample);
        if(tbDeptUsers!=null && tbDeptUsers.size()>0){
            return  tbDeptUsers.get(0);
        }else {
            return  null;
        }
    }

    //根据用户名查询用户权限
    public List<Map<String, Object>> findAuthByLoginName(String loginName) {
        List<Map<String, Object>> permissions= deptUserMapper.selectUserPermissions(loginName);
        return permissions;
    }

    //查询部门用户列表
    public DatatablesPager selectDepartUserList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        List<TbDeptUser> userList = deptUserMapper.selectByExample(new TbDeptUserExample());
        return PageHelper.endPage(userList);
    }

    //删除部门用户
    public int deleteDeptUser(String id) {
        int result = deptUserMapper.deleteByPrimaryKey(Integer.parseInt(id));
        return result;
    }

    //添加部门用户
    public int saveDeptUser(TbDeptUser user) {
        int result = deptUserMapper.insertSelective(user);
        return result;
    }
}
