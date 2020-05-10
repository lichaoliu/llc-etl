package com.llc.etl.admin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.llc.etl.admin.query.CollectionSourceQuery;
import com.llc.etl.admin.service.CollectionSourceService;
import com.llc.llcetlentity.entity.CollectionSourceDO;
import com.llc.llcetlentity.entity.NodeDO;
import com.llc.llcetlentity.result.Return;
import com.llc.llcetlentity.util.IdUtil;
import com.llc.llcetlentity.util.IpTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 23:06
 * @description：数据源管理
 * @modified By：
 * @version: $
 */
@RequestMapping("/admin/collection-source")
@RestController
public class CollectionSourceController {
    @Autowired
    CollectionSourceService collectionSourceService;

    /**
     * 新增
     */
    @PostMapping
    public Return insertNode(@RequestBody CollectionSourceDO collectionSourceDO) {
        Date current = DateTime.now();
        collectionSourceDO.setGmtCreate(current);
        collectionSourceDO.setGmtModified(current);
        collectionSourceDO.setId(IdUtil.nextId());
        collectionSourceDO.setMd5(SecureUtil.md5(collectionSourceDO.getConfig()));
        Boolean flag = collectionSourceService.insert(collectionSourceDO);
        if (flag) {
            return Return.SUCCESS;
        }
        return Return.FAIL;
    }

    /**
     * 分页查询
     *
     * @param collectionSourceQuery
     * @return
     */
    @PostMapping("/query/list")
    public Return pageNode(@RequestBody CollectionSourceQuery collectionSourceQuery) {
        Integer pageNum = collectionSourceQuery.getPageNum();
        Integer pageSize = collectionSourceQuery.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        if (!StringUtils.isEmpty(collectionSourceQuery.getIp())) {
            collectionSourceQuery.setIp(IpTransfer.ipTo39Decimal(collectionSourceQuery.getIp()));
        }
        List<CollectionSourceDO> nodeDOList = collectionSourceService.listCollectionSourceByCondition(collectionSourceQuery);
        Return re = Return.SUCCESS;
        re.put("data", nodeDOList);
        re.put("total", nodeDOList.size());
        return null;
    }

    /**
     * 删除节点
     *
     * @param params
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Return deleteNode(@RequestBody Map<String, Object> params) {
        List<Long> idList = (List<Long>) params.get("id");
        Boolean flag = collectionSourceService.delete(idList);
        if (flag) {
            return Return.SUCCESS;
        }
        return Return.FAIL;
    }

    /**
     * 编辑节点
     *
     * @param collectionSourceDO
     * @return 是否编辑成功
     */
    @PutMapping
    public Return updateNode(@RequestBody CollectionSourceDO collectionSourceDO) {
        Date current = DateTime.now();
        collectionSourceDO.setGmtModified(current);
        collectionSourceDO.setMd5(SecureUtil.md5(collectionSourceDO.getConfig()));
        Boolean flag = collectionSourceService.update(collectionSourceDO);
        if (flag) {
            return Return.SUCCESS;
        }
        return Return.FAIL;
    }
}
