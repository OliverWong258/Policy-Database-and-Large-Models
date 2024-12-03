package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;

@Service
public class ReportService {

    /**
     * 根据回答结果生成Word简报
     * @param reportContent 报告内容
     * @return 下载链接
     */
    public Message generateReport(Message reportContent) {
        // 实现Word文档生成逻辑
        return new Message();
    }
}

