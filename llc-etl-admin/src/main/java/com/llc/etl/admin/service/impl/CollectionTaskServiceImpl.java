package com.llc.etl.admin.service.impl;

import com.llc.etl.admin.dao.CollectionTaskDao;
import com.llc.etl.admin.service.CollectionTaskService;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：llc
 * @date ：Created in 2020/5/14 23:15
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CollectionTaskServiceImpl implements CollectionTaskService {
    @Autowired
    CollectionTaskDao collectionTaskDao;

    @Override
    public Boolean insert(CollectionTaskDO collectionTaskDO) {
        return collectionTaskDao.insert(collectionTaskDO);
    }
}
