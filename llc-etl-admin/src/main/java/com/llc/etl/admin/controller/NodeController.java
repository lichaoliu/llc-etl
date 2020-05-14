package com.llc.etl.admin.controller;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.llc.etl.admin.query.NodeQuery;
import com.llc.etl.admin.service.NodeService;
import com.llc.llcetlentity.entity.NodeDO;
import com.llc.llcetlentity.result.Return;
import com.llc.llcetlentity.util.IdUtil;
import com.llc.llcetlentity.util.IpTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-22 16:09
 */
@RequestMapping("/admin/node")
@RestController
public class NodeController {

    @Autowired
    NodeService nodeService;

    /**
     * 新增
     */
    @PostMapping
    public Return insertNode(@RequestBody NodeDO nodeDO) {
        Date current = DateTime.now();
        nodeDO.setIp(IpTransfer.ipTo39Decimal(nodeDO.getIp()));
        nodeDO.setGmtCreate(current);
        nodeDO.setGmtModified(current);
        nodeDO.setId(IdUtil.nextId());
        Boolean flag = nodeService.insertNode(nodeDO);
        if (!flag) {
            return Return.FAIL();
        }
        return Return.SUCCESS();
    }

    /**
     * 分页查询
     *
     * @param nodeQuery
     * @return
     */
    @PostMapping("/query/list")
    public Return pageNode(@RequestBody NodeQuery nodeQuery) {
        Integer pageNum = nodeQuery.getPageNum();
        Integer pageSize = nodeQuery.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        if (!StringUtils.isEmpty(nodeQuery.getIp())) {
            nodeQuery.setIp(IpTransfer.ipTo39Decimal(nodeQuery.getIp()));
        }
        List<NodeDO> nodeDOList = nodeService.listNodeByCondition(nodeQuery);
        Return re = Return.SUCCESS();
        re.put("data", nodeDOList);
        re.put("total", nodeDOList.size());
        return re;
    }

    /**
     * 删除节点
     *
     * @param params
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Return deleteNode(@RequestBody Map<String, Object> params) {
        List<Long> idList = (List<Long>) params.get("id");
        Boolean flag = nodeService.deleteNodes(idList);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }

    /**
     * 编辑节点
     *
     * @param nodeDO
     * @return 是否编辑成功
     */
    @PutMapping
    public Return updateNode(@RequestBody NodeDO nodeDO) {
        Date current = DateTime.now();
        nodeDO.setGmtModified(current);
        nodeDO.setIp(IpTransfer.ipTo39Decimal(nodeDO.getIp()));
        Boolean flag = nodeService.updateNode(nodeDO);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }

}
