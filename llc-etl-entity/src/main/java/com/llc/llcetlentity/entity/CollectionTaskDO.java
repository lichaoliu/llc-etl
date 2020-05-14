package com.llc.llcetlentity.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 16:42
 * @description：采集任务DO
 * @modified By：
 * @version: $
 */
@Data
public class CollectionTaskDO {
    private Long id;
    private Long sourceId;
    private Long inTopicId;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private Date gmtCreate;
    private Date gmtModified;
    private Long gmtCreateUser;
    private Long gmtModifiedUser;
}
