package com.dingtalk.util;

import com.dingtalk.model.Item;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author nanCheng
 * Date: 2021/11/22
 */
public final class MarkdownBuildUtil {

    private MarkdownBuildUtil() {
    }

    public static String buildMarkdownText(String title, String keywords, List<Item> items, Date sendTime) {
        StringBuilder textSb = new StringBuilder();
        textSb.append(getHeaderText(title, null));
        if (!CollectionUtils.isEmpty(items)) {
            for (Item item : items) {
                textSb.append("\n- ").append(item.getLabel()).append("：").append(item.getValue());
            }
        }
        sendTime = Optional.ofNullable(sendTime).orElse(new Date());
        textSb.append("\n").append(getHeaderText(keywords, 6)).append("&ensp;").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sendTime));
        return textSb.toString();
    }

    /**
     * 构建标题
     *
     * @param text
     * @param level 默认三级
     * @return
     */
    public static String getHeaderText(String text, Integer level) {
        level = Optional.ofNullable(level).orElse(3);
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            numbers.append("#");
        }
        return numbers.append(" ").append(text).toString();
    }


    public static String getItalicText(String input) {
        return "*" + input + "*";
    }

    public static String bold(String input) {
        return "**" + input + "**";
    }

    public static String italics(String input) {
        return "_" + input + "_";
    }

    public static String underline(String input) {
        return "__" + input + "__";
    }


    public static String monospace(String input) {
        return "`" + input + "`";
    }

    /**
     * 代码块
     *
     * @param input
     * @return
     */
    public static String codeBlock(String input) {
        return "```" + input + "```";
    }

    public static String spoiler(String input) {
        return "||" + input + "||";
    }

    public static String strike(String input) {
        return "~~" + input + "~~";
    }

    public static String quote(String input) {
        return "> " + input;
    }

    public static String quote1(String input) {
        return "> " + input.replace("\n", "\n> ");
    }

    public static String quoteBlock(String input) {
        return ">>> " + input;
    }

    public static String maskedLink(String text, String url) {
        return "[" + text.replace("]", "\\]") + "](" + url.replace(")", "%29") + ")";
    }

}
