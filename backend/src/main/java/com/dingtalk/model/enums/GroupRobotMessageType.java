package com.dingtalk.model.enums;

/**
 * @Description 机器人消息类型
 * @Author mutou <mutou.wyn@raycloud.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/8/6
 */
public enum GroupRobotMessageType {

    //文本消息
    TEXT("text"),

    //链接消息
    LINK("link"),

    //markdown消息
    MARKDOWN("markdown"),

    //卡片消息
    ACTION_CARD("actionCard"),

    //feedCard类型
    FEED_CARD("feedCard"),

    //未知
    UNKNOWN("unknown"),
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    GroupRobotMessageType(String value) {
        this.value = value;
    }

    public static GroupRobotMessageType value(String value) {
        GroupRobotMessageType[] values = values();
        for (GroupRobotMessageType val : values) {
            if (value.equals(val.value)) {
                return val;
            }
        }
        return UNKNOWN;
    }

}
