package com.llc.etl.admin.query;

import com.llc.llcetlentity.vo.PageVo;
import lombok.Data;

import java.util.Date;

/**
 * @author ：llc
 * @date ：Created in 2020/5/17 13:33
 * @description：采集任务查询类
 * @modified By：
 * @version: $
 */
@Data
public class CollectionTaskQuery extends PageVo {
    private String collectionSourceName;
    private String nodeName;
    private String inTopic;
    private Integer status;
    private Date startTimeStart;
    private Date startTimeEnd;
}
