package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.entity.Policy;
import com.example.demo.util.WebCrawler;
import com.example.demo.util.XMLExtractor;
import com.example.demo.mapper.PolicyMapper;

@Service
public class DailyUpdateService {

    @Autowired
    private PolicyMapper policyMapper;

    @Value("${app.xml.storage.path}")
    private String xmlStoragePath;

    // 秒 分 时 日 月 周
    @Scheduled(cron = "0 6 2 * * ?")
    public void updateData() {
        // 调用爬虫脚本
        System.out.println("开始爬取最新的xml文件");
        String result = WebCrawler.DailyUpdate(xmlStoragePath); // 若爬取成功，返回文件路径名result
        
        switch (result) {
            case "File exists":
                break;
            case "Not Found":
                break;
            case "Connection out of time":
                break;
            case "Unkonwn Error":
                break;
            default:
                XMLExtractor extractor = XMLExtractor.getInstance(result);
                List<Policy> policies = extractor.extractPolicy();
                /*
                 * 此处暂时省略将政策内容翻译成中文的流程
                 */
                // 插入每个 Policy 对象
                for (Policy policy : policies) {
                    try{
                        policyMapper.insertDocument(
                                policy.getType(),
                                policy.getDate(),
                                policy.getDayOfTheWeek(),
                                policy.getAgency(),
                                policy.getSubagency(),
                                policy.getSubjectJson(),
                                policy.getCfr(),
                                policy.getDepdoc(),
                                policy.getFrdoc(),
                                policy.getBilcod(),
                                policy.getSummary(),
                                policy.getContent()
                        );
                        System.out.println("数据库更新成功");
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println(policy.getDate());
                    }
                }
                break;
        }
    }
}