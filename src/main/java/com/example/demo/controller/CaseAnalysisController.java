package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Message;
import com.example.demo.service.CaseAnalysisService;

@RestController
@RequestMapping("/api/cases")
public class CaseAnalysisController {

    @Autowired
    private CaseAnalysisService caseAnalysisService;

    /**
     * 提交用户的问题，获取案件分析的回答
     * @param question 用户问题
     * @return 回答结果
     */
    @PostMapping("/analysis")
    public Message askCaseQuestion(@RequestBody Message question) {
        // 调用业务逻辑层方法
        return caseAnalysisService.processCaseQuestion(question);
    }
}

