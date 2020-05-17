package com.llc.etl.admin.service.impl;

import com.llc.etl.admin.config.KafkaConfig;
import com.llc.etl.admin.service.KafkaService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author ：llc
 * @date ：Created in 2020/5/17 21:29
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Override
    public Boolean isExistTopic(String topicName) {
        return listTopicNames().contains(topicName);
    }

    @Override
    public Boolean createTopic(String topicName) {
        try {
            Boolean existflag = isExistTopic(topicName);
            Boolean flag = true;
            if (existflag == true) {
                flag = true;
            } else {
                AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfig());
                NewTopic newTopic = new NewTopic(topicName, kafkaConfig.getTopicPartitions(), (short) 1);
                List<NewTopic> topicList = Arrays.asList(newTopic);
                adminClient.createTopics(topicList);
                adminClient.close();
                flag = isExistTopic(topicName);
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<String> listTopicNames() {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfig());
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);
        ListTopicsResult res = adminClient.listTopics(listTopicsOptions);
        Set<String> topicNames = null;
        try {
            topicNames = res.names().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adminClient.close();
        return topicNames;
    }

    @Override
    public Boolean sendDataToTopic(String topicName, String data) {
        ListenableFuture res = kafkaTemplate.send(topicName, data);
        try {
            Boolean flag = new Boolean(true);
            if (res.get() == null) {
                flag = false;
            } else if (res.get() != null) {
                flag = true;
            }
            return flag;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
