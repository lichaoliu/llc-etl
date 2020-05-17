package com.llc.etl.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/17 13:46
 * @description：数据源采集任务VO
 * @modified By：
 * @version: $
 */
@Data
public class CollectionTaskVO {
    /**
     * 任务id
     */
    private Long id;
    private Long collectionSourceId;
    private String collectionSourceName;
    private Long nodeId;
    private String nodeName;
    private String inTopic;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private Date gmtCreate;
}
