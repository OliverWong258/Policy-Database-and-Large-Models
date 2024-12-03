package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Message;
import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 生成Word简报并返回下载链接
     * @param reportRequest 报告请求内容
     * @return 下载链接
     */
    @PostMapping("/generate")
    public Message generateReport(@RequestBody Message reportRequest) {
        // 调用业务逻辑层方法
        return reportService.generateReport(reportRequest);
    }
}

