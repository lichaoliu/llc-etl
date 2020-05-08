package com.llc.etl.admin.controller;

import com.llc.llcetlentity.result.Return;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-22 16:09
 */
@RequestMapping("/admin/node")
@RestController
public class NodeController {

    @PostMapping("/query/list")
    public Return pageNode() {

        return Return.SUCCESS;
    }

}
