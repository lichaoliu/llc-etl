package com.llc.etl.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.llc.llcetlentity.entity.CollectionSourceDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/12 23:36
 * @description：采集源VO
 * @modified By：
 * @version: $
 */
@Data
public class CollectionSourceVO {
    private Long id;
    private String name;
    private Integer sourceType;
    private String config;
    private String characterSet;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

    public static CollectionSourceVO convertFor(CollectionSourceDO collectionSourceDO) {
        CollectionSourceVO vo = new CollectionSourceVO();
        BeanUtils.copyProperties(collectionSourceDO, vo);
        return vo;
    }
}
