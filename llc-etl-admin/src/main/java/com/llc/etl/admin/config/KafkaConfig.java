package com.llc.etl.admin.config;

import com.llc.etl.admin.constant.AdminConstant;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: event-log-transfer
 * @description:
 * @author:
 * @create: 2019-07-11 15:45
 */
@Configuration
@EnableKafka
@Data
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.topic.partitions}")
    private Integer topicPartitions;

    private Integer maxPollRecords = AdminConstant.NUM_500;

    /**
     * <p> 会话过期时长,consumer通过ConsumerCoordinator间歇性发送心跳
     * 超期后,会被认为consumer失效,服务迁移到其他consumer节点
     */
    private int sessionTimeoutMS = AdminConstant.NUM_60000;
    /**
     * <p> 两次poll之间的时间隔间最大值,如果超过此值将会被认为此consumer失效,触发consumer重新平衡.此值必须大于,一个batch所有消息处理时间总和
     */
    private int maxPollIntervalMS = AdminConstant.NUM_30000;
    /**
     * <p> 等待响应的超时时长,如果超出阈值,则会导致请求被重试,取决"retries"参数.
     * 此参数值必须大于sessionTimeoutMS
     */
    private int requestTimeoutMS = AdminConstant.NUM_50000;


    /**
     * <p> alarm消费者部分
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> sampleListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sampleConsumerFactory());
        //开启批量消费
        factory.setBatchListener(true);
        factory.setConcurrency(AdminConstant.NUM_2);
        factory.getContainerProperties().setPollTimeout(AdminConstant.NUM_3000);
        //开启手动ACK.
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }

    public ConsumerFactory<String, String> sampleConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(sampleConsumerConfiguration(), new StringDeserializer(), new StringDeserializer());
    }

    public Map<String, Object> sampleConsumerConfiguration() {
        Map<String, Object> props = new HashMap<>(AdminConstant.NUM_11);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        //强烈建议关闭自动确认,我们使用手动ACK模式,Spring Kafka基于JMS语义为我们设计好了兼容实现.
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, this.maxPollIntervalMS);
        //单次poll允许获取的最多条数
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollRecords);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, this.sessionTimeoutMS);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.requestTimeoutMS);
        return props;
    }
}
