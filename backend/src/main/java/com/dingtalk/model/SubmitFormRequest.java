package com.dingtalk.model;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author nanCheng
 * Date: 2021/12/20
 */
@Data
public class SubmitFormRequest {

    private String link;

    private List<Item> items;

}
