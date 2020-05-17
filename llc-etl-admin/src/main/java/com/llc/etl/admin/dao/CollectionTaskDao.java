package com.llc.etl.admin.dao;

import com.llc.etl.admin.query.CollectionTaskQuery;
import com.llc.etl.admin.vo.CollectionTaskVO;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionTaskDao {
    /**
     * 新增
     *
     * @param collectionTaskDO
     * @return 新增是否成功
     */
    Boolean insert(CollectionTaskDO collectionTaskDO);

    List<CollectionTaskVO> listCollectionTaskByCondition(CollectionTaskQuery collectionTaskQuery);

    Boolean delete(List<Long> idList);

    Boolean updateStatus(Long id, Integer status);

    List<CollectionTaskDO> listCollectionTasks(List<Long> idList);
}
