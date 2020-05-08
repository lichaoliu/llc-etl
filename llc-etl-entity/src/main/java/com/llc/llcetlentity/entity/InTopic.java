package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/7 23:18
 * @description：intopic
 * @modified By：
 * @version: $
 */
public class InTopic {
    @Id
    private Long id;
    private String name;
    private Date gmtCreate;
    private Date gmtModified;
    private Long gmtCreateUser;
    private Long gmtModifiedUser;
}
