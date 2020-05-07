package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 16:38
 * @description：采集节点类
 * @modified By：
 * @version: $
 */

@Entity
@Data
@Table(name = "node")
public class NodeDO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "deploy_path")
    private String deployPath;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name = "gmt_create_user")
    private Long gmtCreateUser;
    @Column(name = "gmt_modified_user")
    private Long gmtModifiedUser;
}
