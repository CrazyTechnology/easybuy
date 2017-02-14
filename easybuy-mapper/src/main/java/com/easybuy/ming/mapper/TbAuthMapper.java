package com.easybuy.ming.mapper;

import com.easybuy.ming.pojo.TbAuth;
import com.easybuy.ming.pojo.TbAuthExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbAuthMapper {
    int countByExample(TbAuthExample example);

    int deleteByExample(TbAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAuth record);

    int insertSelective(TbAuth record);

    List<TbAuth> selectByExample(Map<String, Object> params);

    TbAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAuth record, @Param("example") TbAuthExample example);

    int updateByExample(@Param("record") TbAuth record, @Param("example") TbAuthExample example);

    int updateByPrimaryKeySelective(TbAuth record);

    int updateByPrimaryKey(TbAuth record);

    /**
     * 查询菜单
     *
     * @param type 类型
     * @param parentId 上级菜单
     * @return
     */
    List<TbAuth> selectByParentId(@Param("type") Integer type, @Param("parentId") Integer parentId);

    List<Map<String, Object>> findMenuJson(@Param("type") Integer type, @Param("parentId") Integer parentId, @Param("loginName") String loginName);
}