package com.llc.etl.admin.service.impl;

import com.llc.etl.admin.dao.NodeDao;
import com.llc.etl.admin.query.NodeQuery;
import com.llc.etl.admin.service.NodeService;
import com.llc.llcetlentity.entity.NodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 13:27
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    NodeDao nodeDao;

    @Override
    public Boolean insertNode(NodeDO nodeDO) {
        return nodeDao.insertNode(nodeDO);
    }

    @Override
    public List<NodeDO> listNodeByCondition(NodeQuery nodeQuery) {
        return nodeDao.listNodeByCondition(nodeQuery);
    }

    @Override
    public Boolean deleteNodes(List<Long> idList) {
        return nodeDao.deleteNodes(idList);
    }

    @Override
    public Boolean updateNode(NodeDO nodeDO) {
        return nodeDao.updateNode(nodeDO);
    }
}
