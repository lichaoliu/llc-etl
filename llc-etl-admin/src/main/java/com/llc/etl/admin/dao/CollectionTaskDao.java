package com.llc.etl.admin.dao;

import com.llc.llcetlentity.entity.CollectionTaskDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionTaskDao {
    /**
     * 新增
     *
     * @param collectionTaskDO
     * @return 新增是否成功
     */
    Boolean insert(CollectionTaskDO collectionTaskDO);
}
