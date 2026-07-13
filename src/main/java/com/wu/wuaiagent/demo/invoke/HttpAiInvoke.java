package com.wu.wuaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * Http 方式调用AI
 */
public class HttpAiInvoke {
    public static void main(String[] args) {
        // 从环境变量获取 API Key
        String apiKey = TestApiKey.API_KEY;

        // 请求 URL
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        // 构建请求体 JSON
        String requestBody = JSONUtil.createObj()
                .set("model", "qwen-plus")
                .set("input", JSONUtil.createObj()
                        .set("messages", JSONUtil.createArray()
                                .put(JSONUtil.createObj().set("role", "system").set("content", "You are a helpful assistant."))
                                .put(JSONUtil.createObj().set("role", "user").set("content", "你是谁？"))
                        )
                )
                .set("parameters", JSONUtil.createObj().set("result_format", "message"))
                .toString();

        // 发送 POST 请求并获取响应
        String response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .execute()
                .body();

        // 输出响应内容
        System.out.println(response);
    }
}