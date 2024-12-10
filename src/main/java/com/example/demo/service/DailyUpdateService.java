package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DailyUpdateService {

    // 秒 分 时 日 月 周
    @Scheduled(cron = "0 50 23 * * ?")
    public void updateData() {
        // 调用爬虫脚本的逻辑（占位符）
        // crawlerScript.run();
        System.out.println("定时任务执行：调用爬虫处理数据");
    }
}