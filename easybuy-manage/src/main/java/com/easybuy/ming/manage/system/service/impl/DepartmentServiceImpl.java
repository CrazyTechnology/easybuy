package com.easybuy.ming.manage.system.service.impl;

import com.easybuy.ming.mapper.TbDeptMapper;
import com.easybuy.ming.pojo.*;
import com.easybuy.ming.manage.system.service.DepartmentService;
import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/12/4. 部门管理业务
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private TbDeptMapper tbDeptMapper;

    /**
     * 分页查询部门信息
     * @param datatablesPager 分页信息
     * @return
     */

    public DatatablesPager selectDepartmentList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        Map<String, Object> params = datatablesPager.getParams();
        List<TbDept> userList = tbDeptMapper.selectByExample(params);
        return PageHelper.endPage(userList);
    }

    /**
     * 查询部门详情
     *
     * @param id
     * @return
     */
    public TbDept selectDepartDetail(String id) {
        TbDept dept = tbDeptMapper.selectByPrimaryKey(Integer.parseInt(id));
        return dept;
    }
}
