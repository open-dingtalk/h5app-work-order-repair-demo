package com.dingtalk.controller;

import com.alibaba.fastjson.JSON;
import com.dingtalk.model.RpcServiceResult;
import com.dingtalk.model.SubmitFormRequest;
import com.dingtalk.service.GroupRobotManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 群机器人业务Controller
 */
@Slf4j
@RestController
@RequestMapping("/groupRobot")
public class GroupRobotController {

    @Autowired
    private GroupRobotManager groupRobotManager;

    @RequestMapping(value = "/test/msg", method = RequestMethod.GET)
    public RpcServiceResult testMsg(@RequestParam String accessToken) {
        return RpcServiceResult.getSuccessResult(groupRobotManager.testMsg(accessToken));
    }

    /**
     * 提交表单
     */
    @RequestMapping(value = "/form/submit", method = RequestMethod.POST)
    public RpcServiceResult submitForm(@RequestBody SubmitFormRequest submitFormRequest) {
        log.info("提交表单 \n{}", JSON.toJSONString(submitFormRequest));
        return RpcServiceResult.getSuccessResult(groupRobotManager.submitForm(submitFormRequest));
    }
}
