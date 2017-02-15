package com.easybuy.ming.system.service.impl;

import com.easybuy.ming.mapper.TbRoleAuthMapper;
import com.easybuy.ming.mapper.TbRoleMapper;
import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbRole;
import com.easybuy.ming.pojo.TbRoleAuth;
import com.easybuy.ming.pojo.TbRoleExample;
import com.easybuy.ming.system.service.RoleService;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-01-18.
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private TbRoleAuthMapper tbRoleAuthMapper;

    @Resource
    private TbRoleMapper tbRoleMapper;


    /**
     * 角色列表
     *
     * @param datatablesPager
     * @return
     */
    public DatatablesPager selectRoleList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        List<TbRole> tbRoles = tbRoleMapper.selectByExample(new TbRoleExample());
        return PageHelper.endPage(tbRoles);
    }

    /**
     * 保存分配的权限
     *
     * @param roleAuth
     * @return
     */
    public Integer saveAuth(TbRoleAuth roleAuth) {
        return tbRoleAuthMapper.insertSelective(roleAuth);
    }
}
