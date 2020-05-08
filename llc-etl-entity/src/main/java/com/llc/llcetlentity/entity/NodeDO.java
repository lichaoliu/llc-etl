package com.llc.llcetlentity.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 16:38
 * @description：采集节点类
 * @modified By：
 * @version: $
 */


@Data
public class NodeDO {
    private Long id;
    private String ip;
    private String name;
    private Date lastHeartTime;
    private Date gmtCreate;
    private Date gmtModified;
    private Long gmtCreateUser;
    private Long gmtModifiedUser;
}
