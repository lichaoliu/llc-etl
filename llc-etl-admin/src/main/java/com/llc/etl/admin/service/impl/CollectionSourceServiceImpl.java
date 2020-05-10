package com.llc.etl.admin.service.impl;

import com.llc.etl.admin.dao.CollectionSourceDao;
import com.llc.etl.admin.query.CollectionSourceQuery;
import com.llc.etl.admin.service.CollectionSourceService;
import com.llc.llcetlentity.entity.CollectionSourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 23:08
 * @description：数据源service接口实现
 * @modified By：
 * @version: $
 */
@Service
public class CollectionSourceServiceImpl implements CollectionSourceService {
    @Autowired
    CollectionSourceDao collectionSourceDao;

    @Override
    public Boolean insert(CollectionSourceDO collectionSourceDO) {
        return collectionSourceDao.insert(collectionSourceDO);
    }

    @Override
    public List<CollectionSourceDO> listCollectionSourceByCondition(CollectionSourceQuery collectionSourceQuery) {
        return collectionSourceDao.listCollectionSourceByCondition(collectionSourceQuery);
    }

    @Override
    public Boolean delete(List<Long> idList) {
        return collectionSourceDao.delete(idList);
    }

    @Override
    public Boolean update(CollectionSourceDO collectionSourceDO) {
        return collectionSourceDao.update(collectionSourceDO);
    }
}
