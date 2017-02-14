package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbItemParamGroup;
import com.easybuy.ming.pojo.TbItemParamGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbItemParamGroupMapper {
    int countByExample(TbItemParamGroupExample example);

    int deleteByExample(TbItemParamGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbItemParamGroup record);

    int insertSelective(TbItemParamGroup record);

    List<TbItemParamGroup> selectByExample(TbItemParamGroupExample example);

    TbItemParamGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbItemParamGroup record, @Param("example") TbItemParamGroupExample example);

    int updateByExample(@Param("record") TbItemParamGroup record, @Param("example") TbItemParamGroupExample example);

    int updateByPrimaryKeySelective(TbItemParamGroup record);

    int updateByPrimaryKey(TbItemParamGroup record);
}