package com.llc.etl.admin.service;

import com.llc.etl.admin.query.CollectionTaskQuery;
import com.llc.etl.admin.vo.CollectionTaskVO;
import com.llc.llcetlentity.entity.CollectionTaskDO;

import java.util.List;

public interface CollectionTaskService {

    Boolean insert(CollectionTaskDO collectionTaskDO);

    List<CollectionTaskVO> listCollectionTaskByCondition(CollectionTaskQuery collectionTaskQuery);

    Boolean delete(List<Long> idList);

    Boolean updateStatus(Long id, Integer status);

    List<CollectionTaskDO> listCollectionTasks(List<Long> idList);
}
