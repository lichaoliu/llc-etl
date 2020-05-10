package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "节点ip不能为空")
    private String ip;
    @NotBlank(message = "节点名称不能为空")
    private String name;
    private Date lastHeartTime;
    private Date gmtCreate;
    private Date gmtModified;
    private Long gmtCreateUser;
    private Long gmtModifiedUser;
}
