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
@Data
public class CollectionSourceDO {
    private Long id;
    private String name;
    private Integer sourceType;
    private String md5;
    private String config;
    private String characterSet;
    private Date gmtCreate;
    private Date gmtModified;
    private Long gmtCreateUser;
    private Long gmtModifiedUser;
}
