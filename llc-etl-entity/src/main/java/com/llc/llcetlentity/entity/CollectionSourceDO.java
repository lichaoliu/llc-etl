package com.llc.llcetlentity.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "采集源名称不能为空")
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
