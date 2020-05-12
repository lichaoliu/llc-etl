package com.llc.etl.admin.query;

import com.llc.llcetlentity.vo.PageVo;
import lombok.Data;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 23:51
 * @description：数据源分页查询Query
 * @modified By：
 * @version: $
 */
@Data
public class CollectionSourceQuery extends PageVo {
    private String name;
    private Integer sourceType;
}
