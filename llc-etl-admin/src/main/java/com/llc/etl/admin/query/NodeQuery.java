package com.llc.etl.admin.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.llc.llcetlentity.vo.PageVo;
import lombok.Data;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 15:38
 * @description：node查询类
 * @modified By：
 * @version: $
 */
@Data
public class NodeQuery extends PageVo {
    private String name;
    private String ip;
}
