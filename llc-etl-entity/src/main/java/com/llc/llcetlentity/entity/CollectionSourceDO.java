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
@Table(name = "collection_source")
public class CollectionSourceDO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "source_type")
    private Integer sourceType;
    @Column(name = "md5")
    private String md5;
    @Column(name = "config")
    private String config;
    @Column(name = "character_set")
    private String characterSet;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name = "gmt_create_user")
    private Long gmtCreateUser;
    @Column(name = "gmt_modified_user")
    private Long gmtModifiedUser;
}
