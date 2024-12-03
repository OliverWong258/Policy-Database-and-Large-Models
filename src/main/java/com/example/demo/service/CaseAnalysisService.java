package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CaseAnalysisService {

    @Autowired
    private LargeModelAPI largeModelAPI;

    @Autowired
    private InformationRetriever informationRetriever;

    /**
     * 处理用户的案件分析问答请求
     * @param question 用户问题
     * @return 回答结果
     */
    public Message processCaseQuestion(Message question) {
        // 解析问题，检索案件信息，调用大模型生成分析
    }
}
