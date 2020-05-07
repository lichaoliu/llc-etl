package com.llc.etl.admin.controller;

import com.llc.llcetlentity.result.ReturnT;
import org.springframework.stereotype.Controller;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-22 16:09
 */
@Controller
public class IndexController {

    public ReturnT<String> loginDo() {

        return ReturnT.SUCCESS;
    }

}
