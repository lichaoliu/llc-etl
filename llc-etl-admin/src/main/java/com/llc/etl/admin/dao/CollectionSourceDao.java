package com.llc.etl.admin.dao;

import com.llc.etl.admin.query.CollectionSourceQuery;
import com.llc.llcetlentity.entity.CollectionSourceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionSourceDao {
    /**
     * 新增
     *
     * @param collectionSourceDO
     * @return 新增是否成功
     */
    Boolean insert(CollectionSourceDO collectionSourceDO);


    List<CollectionSourceDO> listCollectionSourceByCondition(CollectionSourceQuery collectionSourceQuery);

    Boolean delete(List<Long> idList);

    Boolean update(CollectionSourceDO collectionSourceDO);
}
