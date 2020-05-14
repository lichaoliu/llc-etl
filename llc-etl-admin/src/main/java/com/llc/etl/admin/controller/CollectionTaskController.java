package com.llc.etl.admin.controller;

import cn.hutool.core.date.DateTime;
import com.llc.etl.admin.service.CollectionTaskService;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import com.llc.llcetlentity.result.Return;
import com.llc.llcetlentity.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/14 23:10
 * @description：采集任务管理
 * @modified By：
 * @version: $
 */
@RequestMapping("/admin/collection-task")
@RestController
public class CollectionTaskController {

    @Autowired
    CollectionTaskService collectionTaskService;

    /**
     * 新增
     */
    @PostMapping
    public Return insertNode(@RequestBody CollectionTaskDO collectionTaskDO) {
        Date current = DateTime.now();
        collectionTaskDO.setGmtCreate(current);
        collectionTaskDO.setGmtModified(current);
        collectionTaskDO.setId(IdUtil.nextId());
        Boolean flag = collectionTaskService.insert(collectionTaskDO);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }

//    /**
//     * 分页查询
//     *
//     * @param nodeQuery
//     * @return
//     */
//    @PostMapping("/query/list")
//    public Return pageNode(@RequestBody NodeQuery nodeQuery) {
//        Integer pageNum = nodeQuery.getPageNum();
//        Integer pageSize = nodeQuery.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        if (!StringUtils.isEmpty(nodeQuery.getIp())) {
//            nodeQuery.setIp(IpTransfer.ipTo39Decimal(nodeQuery.getIp()));
//        }
//        List<NodeDO> nodeDOList = collectionTaskService.listNodeByCondition(nodeQuery);
//        Return re = Return.SUCCESS();
//        re.put("data", nodeDOList);
//        re.put("total", nodeDOList.size());
//        return re;
//    }
}
