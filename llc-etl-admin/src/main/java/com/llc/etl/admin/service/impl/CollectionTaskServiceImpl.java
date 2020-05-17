package com.llc.etl.admin.service.impl;

import com.llc.etl.admin.dao.CollectionTaskDao;
import com.llc.etl.admin.query.CollectionTaskQuery;
import com.llc.etl.admin.service.CollectionTaskService;
import com.llc.etl.admin.vo.CollectionTaskVO;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public List<CollectionTaskVO> listCollectionTaskByCondition(CollectionTaskQuery collectionTaskQuery) {
        return collectionTaskDao.listCollectionTaskByCondition(collectionTaskQuery);
    }

    @Override
    public Boolean delete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return true;
        }
        return collectionTaskDao.delete(idList);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        return collectionTaskDao.updateStatus(id, status);
    }

    @Override
    public List<CollectionTaskDO> listCollectionTasks(List<Long> idList) {
        return collectionTaskDao.listCollectionTasks(idList);
    }

}
