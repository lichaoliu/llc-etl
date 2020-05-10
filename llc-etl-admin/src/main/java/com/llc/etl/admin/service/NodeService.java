package com.llc.etl.admin.service;

import com.llc.etl.admin.query.NodeQuery;
import com.llc.llcetlentity.entity.NodeDO;

import java.util.List;

public interface NodeService {

    Boolean insertNode(NodeDO nodeDO);

    List<NodeDO> listNodeByCondition(NodeQuery nodeQuery);

    Boolean deleteNodes(List<Long> idList);

    Boolean updateNode(NodeDO nodeDO);
}
