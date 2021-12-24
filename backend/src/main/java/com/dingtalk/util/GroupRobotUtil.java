package com.dingtalk.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.model.enums.GroupRobotMessageType;
import com.taobao.api.ApiException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 群机器人工具类
 */
public class GroupRobotUtil {

    private static Logger logger = LoggerFactory.getLogger(GroupRobotUtil.class);

    /**
     * 发送actionCard消息
     */
    public static OapiRobotSendResponse sendMessage(String robotHookUrl, OapiRobotSendRequest.Actioncard actionCard, OapiRobotSendRequest.At at) {
        OapiRobotSendResponse response = null;
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(GroupRobotMessageType.ACTION_CARD.getValue());
            request.setActionCard(actionCard);
            if (at != null) {
                request.setAt(at);
            }
            response = execute(request, robotHookUrl);
        } catch (ApiException e) {
            logger.error("群机器人发送actionCard消息失败!", e);
        }
        return response;
    }

    /**
     * 发送feedCard消息
     */
    public static OapiRobotSendResponse sendMessage(String robotHookUrl, OapiRobotSendRequest.Feedcard feedCard, OapiRobotSendRequest.At at) {
        OapiRobotSendResponse response = null;
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(GroupRobotMessageType.FEED_CARD.getValue());
            request.setFeedCard(feedCard);
            if (at != null) {
                request.setAt(at);
            }
            response = execute(request, robotHookUrl);
        } catch (ApiException e) {
            logger.error("群机器人发送feedCard消息失败!", e);
        }
        return response;
    }

    /**
     * 发送link消息
     */
    public static OapiRobotSendResponse sendMessage(String robotHookUrl, OapiRobotSendRequest.Link link, OapiRobotSendRequest.At at) {
        OapiRobotSendResponse response = null;
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(GroupRobotMessageType.LINK.getValue());
            request.setLink(link);
            if (at != null) {
                request.setAt(at);
            }
            response = execute(request, robotHookUrl);
        } catch (ApiException e) {
            logger.error("群机器人发送link消息失败!", e);
        }
        return response;
    }

    /**
     * 发送markdown消息
     */
    public static OapiRobotSendResponse sendMessage(String robotHookUrl, OapiRobotSendRequest.Markdown markdown, OapiRobotSendRequest.At at) {
        OapiRobotSendResponse response = null;
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(GroupRobotMessageType.MARKDOWN.getValue());
            request.setMarkdown(markdown);
            if (at != null) {
                request.setAt(at);
            }
            response = execute(request, robotHookUrl);
        } catch (ApiException e) {
            logger.error("群机器人发送actionCard消息失败!", e);
        }
        return response;
    }

    /**
     * 发送text消息
     */
    public static OapiRobotSendResponse sendMessage(String robotHookUrl, OapiRobotSendRequest.Text text, OapiRobotSendRequest.At at) {
        OapiRobotSendResponse response = null;
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(GroupRobotMessageType.TEXT.getValue());
            request.setText(text);
            if (at != null) {
                request.setAt(at);
            }
            response = execute(request, robotHookUrl);
        } catch (ApiException e) {
            logger.error("群机器人发送text消息失败!", e);
        }
        return response;
    }

    private static <T extends TaobaoResponse> T execute(BaseTaobaoRequest<T> request, String url) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(url);
        try {
            return client.execute(request);
        } catch (IllegalArgumentException e) {
            logger.error("钉钉接口参数异常", e);
            throw new ApiException("钉钉接口参数异常");
        }
    }

}
