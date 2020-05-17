package com.llc.etl.admin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import com.llc.etl.admin.service.KafkaService;
import com.llc.llcetlentity.entity.CollectionSourceDO;
import com.llc.llcetlentity.entity.CollectionTaskDO;
import com.llc.llcetlentity.result.Return;
import com.llc.llcetlentity.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ：llc
 * @date ：Created in 2020/5/17 21:41
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/admin/in-topic")
public class InTopicController {
    @Autowired
    KafkaService kafkaService;

    /**
     * 新增
     */
    @PostMapping
    public Return insertInTopic(@RequestBody Map<String, Object> params) {
        String name = params.get("name").toString();
        Boolean flag = kafkaService.createTopic(name);
        if (flag) {
            return Return.SUCCESS();
        }
        return Return.FAIL();
    }

    /**
     * 分页查询
     */
    @PostMapping("/list/query")
    public Return pageInTopic(@RequestBody Map<String, Object> params) {
        Object name = params.get("name");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        Set<String> topicSet = kafkaService.listTopicNames();
        List<String> topicList = null;
        if (name == null) {
            topicList = new ArrayList<>(topicSet);
        } else {
            topicList = new ArrayList<>();
            for (String topic : topicSet) {
                if (topic.contains(name.toString())) {
                    topicList.add(topic);
                }
            }
        }
        topicList.sort(Comparator.naturalOrder());
        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;
        Return re = Return.SUCCESS();
        if (topicList.size() > end) {
            re.put("data", topicList.subList(start, end));
        } else {
            re.put("data", topicList.subList(start, topicList.size()));
        }
        return re;
    }
}
