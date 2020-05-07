package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 16:42
 * @description：采集任务DO
 * @modified By：
 * @version: $
 */
@Entity
@Data
@Table(name = "collection_task")
public class CollectionTaskDO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "in_topic_id")
    private Long inTopicId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name = "gmt_create_user")
    private Long gmtCreateUser;
    @Column(name = "gmt_modified_user")
    private Long gmtModifiedUser;
}
