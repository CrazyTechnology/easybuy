package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbItemParamValue;
import com.easybuy.ming.pojo.TbItemParamValueExample;
import com.easybuy.ming.pojo.TbItemParamValueKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbItemParamValueMapper {
    int countByExample(TbItemParamValueExample example);

    int deleteByExample(TbItemParamValueExample example);

    int deleteByPrimaryKey(TbItemParamValueKey key);

    int insert(TbItemParamValue record);

    int insertSelective(TbItemParamValue record);

    List<TbItemParamValue> selectByExample(TbItemParamValueExample example);

    TbItemParamValue selectByPrimaryKey(TbItemParamValueKey key);

    int updateByExampleSelective(@Param("record") TbItemParamValue record, @Param("example") TbItemParamValueExample example);

    int updateByExample(@Param("record") TbItemParamValue record, @Param("example") TbItemParamValueExample example);

    int updateByPrimaryKeySelective(TbItemParamValue record);

    int updateByPrimaryKey(TbItemParamValue record);
}