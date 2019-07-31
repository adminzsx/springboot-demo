package com.manage.webmgr.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.webmgr.user.entity.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    //删除收藏
    int deleteByCondition(Collection record);
    
    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
    
    //根据条件查询
    List<Collection> selectByCondition(Collection record);

    //根据条件查询
    Collection selectByCIdAndEId(@Param("customerid")Integer customerid,@Param("employeeid")Integer employeeid);
}