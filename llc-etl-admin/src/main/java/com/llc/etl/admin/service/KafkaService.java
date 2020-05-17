package com.llc.etl.admin.service;

import java.util.Set;

/**
 * kafkaservice
 */
public interface KafkaService {
    /**
     * 发送数据到指定的topic中
     *
     * @param topicName topic名称
     * @param data      数据
     * @return 发送的状态
     */
    Boolean sendDataToTopic(String topicName, String data);


    /**
     * 校验topic是否已经存在于kafka中
     *
     * @param topicName topic的名称
     * @return 是否存在的状态
     */
    Boolean isExistTopic(String topicName);


    /**
     * 创建指定的topic
     *
     * @param topicName topic的名称
     * @return 创建topic是否成功的状态
     */
    Boolean createTopic(String topicName);

    /**
     * 获取所有topic名称
     *
     * @return kafka中所有topic名称
     */
    Set<String> listTopicNames();
}
