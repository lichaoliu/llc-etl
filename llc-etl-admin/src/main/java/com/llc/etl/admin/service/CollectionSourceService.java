package com.llc.etl.admin.service;

import com.llc.etl.admin.query.CollectionSourceQuery;
import com.llc.etl.admin.vo.CollectionSourceVO;
import com.llc.etl.admin.vo.CollectionSourcebuttonVO;
import com.llc.llcetlentity.entity.CollectionSourceDO;

import java.util.List;

public interface CollectionSourceService {

    Boolean insert(CollectionSourceDO collectionSourceDO);

    List<CollectionSourceVO> listCollectionSourceByCondition(CollectionSourceQuery collectionSourceQuery);

    Boolean delete(List<Long> idList);

    Boolean update(CollectionSourceDO collectionSourceDO);

    List<CollectionSourcebuttonVO> listCollectionSourcebuttons();
}
