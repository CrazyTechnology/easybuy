package com.easybuy.ming.manage.system.service;

import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbDept;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ming on 2016/12/4.
 */
public interface DepartmentService {

    DatatablesPager selectDepartmentList(DatatablesPager datatablesPager);

    /**
     * 根据订单id查询订单详情
     *
     * @param id
     * @return
     */
    TbDept selectDepartDetail(String id);
}
