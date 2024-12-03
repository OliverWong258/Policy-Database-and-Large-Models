package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Message;
import com.example.demo.service.QnAService;

@RestController
@RequestMapping("/api/qna")
public class QnAController {

    @Autowired
    private QnAService qnAService;

    /**
     * 提交用户的问题，获取政策相关的回答
     * @param question 用户问题
     * @return 回答结果
     */
    @PostMapping("/policy")
    public Message askPolicyQuestion(@RequestBody Message question) {
        // 调用业务逻辑层方法
        return qnAService.processPolicyQuestion(question);
    }
}

