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
@Table(name = "extract_task")
public class ExtractTaskDO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "ip",length = 39)
    private String ip;
    @Column(name = "execute_time")
    private Date executeTime;
    /**
     * 任务状态 1已启动 2异常 3已停止
     */
    @Column(name = "status")
    private Integer status;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name = "gmt_create_user")
    private Long gmtCreateUser;
    @Column(name = "gmt_modified_user")
    private Long gmtModifiedUser;
}
