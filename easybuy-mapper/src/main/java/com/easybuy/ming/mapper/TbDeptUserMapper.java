package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.pojo.TbDeptUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbDeptUserMapper {
    int countByExample(TbDeptUserExample example);

    int deleteByExample(TbDeptUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDeptUser record);

    int insertSelective(TbDeptUser record);

    List<TbDeptUser> selectByExample(TbDeptUserExample example);

    TbDeptUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDeptUser record, @Param("example") TbDeptUserExample example);

    int updateByExample(@Param("record") TbDeptUser record, @Param("example") TbDeptUserExample example);

    int updateByPrimaryKeySelective(TbDeptUser record);

    int updateByPrimaryKey(TbDeptUser record);

    List<Map<String, Object>> selectUserPermissions(String loginName);
}