package com.llc.etl.admin.constant;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ：llc
 * @date ：Created in 2020/5/17 20:28
 * @description：
 * @modified By：
 * @version: $
 */
public class Test {
    public static void main(String[] argus) {
        List<String> topicList = new ArrayList<>();
        topicList.add("b");
        topicList.add("c");
        topicList.add("a");

        topicList.sort(Comparator.naturalOrder());
        System.out.println(topicList);
    }
}
