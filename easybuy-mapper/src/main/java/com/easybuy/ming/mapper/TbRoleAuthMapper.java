package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbRoleAuth;
import com.easybuy.ming.pojo.TbRoleAuthExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbRoleAuthMapper {
    int countByExample(TbRoleAuthExample example);

    int deleteByExample(TbRoleAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRoleAuth record);

    int insertSelective(TbRoleAuth record);

    List<TbRoleAuth> selectByExample(TbRoleAuthExample example);

    TbRoleAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRoleAuth record, @Param("example") TbRoleAuthExample example);

    int updateByExample(@Param("record") TbRoleAuth record, @Param("example") TbRoleAuthExample example);

    int updateByPrimaryKeySelective(TbRoleAuth record);

    int updateByPrimaryKey(TbRoleAuth record);
}