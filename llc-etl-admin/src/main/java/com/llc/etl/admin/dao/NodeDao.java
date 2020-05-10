package com.llc.etl.admin.dao;

import com.llc.etl.admin.query.NodeQuery;
import com.llc.llcetlentity.entity.NodeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NodeDao {

    /**
     * 添加节点
     *
     * @param nodeDO
     * @return 是否添加成功
     */
    Boolean insertNode(NodeDO nodeDO);

    /**
     * 根据条件查询节点
     *
     * @param nodeQuery
     * @return 节点集合
     */
    List<NodeDO> listNodeByCondition(NodeQuery nodeQuery);

    /**
     * 删除节点
     *
     * @param idList
     * @return 是否删除成功
     */
    Boolean deleteNodes(List<Long> idList);

    /**
     * 更新节点信息
     *
     * @param nodeDO
     * @return 是否更新成功
     */
    Boolean updateNode(NodeDO nodeDO);

}
