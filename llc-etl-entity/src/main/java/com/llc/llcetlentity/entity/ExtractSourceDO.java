package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 16:41
 * @description：采集源DO
 * @modified By：
 * @version: $
 */
@Entity
@Data
@Table(name = "extract_source")
public class ExtractSourceDO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "node_id")
    private Long nodeId;
    @Column(name = "extract_type")
    private Integer extractType;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "route")
    private String route;
    @Column(name = "protocol_type")
    private Integer protocolType;
    @Column(name = "port")
    private String port;
    @Column(name = "kafka_address")
    private String kafkaAddress;
    @Column(name = "source_topic")
    private String sourceTopic;
    @Column(name = "character_set")
    private Integer characterSet;
    @Column(name = "destination_topic")
    private String destinationTopic;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name = "gmt_create_user")
    private Long gmtCreateUser;
    @Column(name = "gmt_modified_user")
    private Long gmtModifiedUser;
}
