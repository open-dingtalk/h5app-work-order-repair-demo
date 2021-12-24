package com.dingtalk.service;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.model.SubmitFormRequest;
import com.dingtalk.util.GroupRobotUtil;
import com.dingtalk.util.MarkdownBuildUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 群机器人业务service
 */
@Service
public class GroupRobotManager {

    public OapiRobotSendResponse testMsg(String accessToken) {
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("工单消息通知\n测试消息");
        return GroupRobotUtil.sendMessage("https://oapi.dingtalk.com/robot/send?access_token=" + accessToken, text, null);
    }

    public boolean submitForm(SubmitFormRequest submitFormRequest) {
        String title = "您有新的工单提交";

        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();

        markdown.setTitle(title);
        markdown.setText(MarkdownBuildUtil.buildMarkdownText(title, "工单消息", submitFormRequest.getItems(), new Date()));

        OapiRobotSendResponse response = GroupRobotUtil.sendMessage(submitFormRequest.getLink(), markdown, null);
        return response != null && response.isSuccess();
    }
}
