package com.llc.etl.admin.controller;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.llc.etl.admin.config.KafkaConfig;
import com.llc.etl.admin.query.CollectionTaskQuery;
import com.llc.etl.admin.service.CollectionTaskService;
import com.llc.etl.admin.service.KafkaService;
import com.llc.etl.admin.vo.CollectionTaskVO;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import com.llc.llcetlentity.result.Return;
import com.llc.llcetlentity.util.IdUtil;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        //新增成功 任务初始状态为停止
        collectionTaskDO.setStatus(2);
        Boolean flag = collectionTaskService.insert(collectionTaskDO);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }

    /**
     * 分页查询
     *
     * @param collectionTaskQuery
     * @return
     */
    @PostMapping("/list/query")
    public Return pageNode(@RequestBody CollectionTaskQuery collectionTaskQuery) {
        Integer pageNum = collectionTaskQuery.getPageNum();
        Integer pageSize = collectionTaskQuery.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<CollectionTaskVO> nodeDOList = collectionTaskService.listCollectionTaskByCondition(collectionTaskQuery);
        Return re = Return.SUCCESS();
        re.put("data", nodeDOList);
        re.put("total", nodeDOList.size());
        return re;
    }

    /**
     * 删除
     *
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public Return delete(@RequestBody Map<String, Object> params) {
        List<Long> idList = (List<Long>) params.get("id");
        List<CollectionTaskDO> collectionTaskDOList = collectionTaskService.listCollectionTasks(idList);
        List<Long> runTasks = new ArrayList<>();
        List<Long> stopTasks = new ArrayList<>();
        for (CollectionTaskDO taskDO : collectionTaskDOList) {
            //运行状态不能删除
            if (taskDO.getStatus() == 1) {
                runTasks.add(taskDO.getId());
            } else {
                stopTasks.add(taskDO.getId());
            }
        }
        collectionTaskService.delete(stopTasks);
        Return re = Return.SUCCESS();
        re.put("success", stopTasks);
        re.put("fail", runTasks);
        return re;
    }

    /**
     * 更改任务状态
     *
     * @param params
     * @return 是否更改成功
     */
    @PostMapping("/status")
    public Return updateStatus(@RequestBody Map<String, Object> params) {
        Integer status = (Integer) params.get("status");
        Long id = (Long) params.get("id");
        Boolean flag = collectionTaskService.updateStatus(id, status);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }
}
