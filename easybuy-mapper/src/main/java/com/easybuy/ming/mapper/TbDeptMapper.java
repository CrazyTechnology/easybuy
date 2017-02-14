package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbDept;
import com.easybuy.ming.pojo.TbDeptExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbDeptMapper {
    int countByExample(TbDeptExample example);

    int deleteByExample(TbDeptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDept record);

    int insertSelective(TbDept record);

    List<TbDept> selectByExample(Map map);

    TbDept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDept record, @Param("example") TbDeptExample example);

    int updateByExample(@Param("record") TbDept record, @Param("example") TbDeptExample example);

    int updateByPrimaryKeySelective(TbDept record);

    int updateByPrimaryKey(TbDept record);
}