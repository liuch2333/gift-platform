package com.gift.baseinfo.main.controller;

import com.gift.baseinfo.main.service.CommonService;
import com.gift.tools.apis.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuch
 * @title: CommonController
 * @description: 其他服务
 * @date 2021/10/17 10:43
 */
@Api(tags = "其他服务-附件等")
@RestController
public class CommonController {

    @Autowired
    CommonService commonService;

    /**
     * @author liuch
     * @description 单独上传资料，保存上传数据，不对应条目信息
     * @date 2021/9/17 9:24
     * @param
     * @param files
     * @return com.hd.tool.api.R 只返回本次上传结果
     */
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "uploadFile")
    public R uploadNoDatum(@ApiParam("图片信息") @RequestPart(value = "files") MultipartFile[] files) {
        return commonService.upload(files);
    }
}
