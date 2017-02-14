package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbItemParamKey;
import com.easybuy.ming.pojo.TbItemParamKeyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbItemParamKeyMapper {
    int countByExample(TbItemParamKeyExample example);

    int deleteByExample(TbItemParamKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbItemParamKey record);

    int insertSelective(TbItemParamKey record);

    List<TbItemParamKey> selectByExample(TbItemParamKeyExample example);

    TbItemParamKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbItemParamKey record, @Param("example") TbItemParamKeyExample example);

    int updateByExample(@Param("record") TbItemParamKey record, @Param("example") TbItemParamKeyExample example);

    int updateByPrimaryKeySelective(TbItemParamKey record);

    int updateByPrimaryKey(TbItemParamKey record);
}