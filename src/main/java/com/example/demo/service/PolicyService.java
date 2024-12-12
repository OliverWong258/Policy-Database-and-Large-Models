package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Policy;
import com.example.demo.mapper.PolicyMapper;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

@Service
public class PolicyService {

    @Autowired
    private PolicyMapper policyMapper;

    /**
     * 根据查询条件搜索政策
     * @param keywords 关键词
     * @param department 颁布部门
     * @param publishDate 发布日期
     * @return 政策列表
     */
    public List<Policy> searchPolicies(String keywords, String department, LocalDate publishDate) {
        Date date = publishDate != null ? Date.valueOf(publishDate) : null;
        //return policyMapper.searchPolicies(keywords, department, date);
        // 调用方法查找政策
        List<Policy> policies = policyMapper.searchPolicies(keywords, department, date);

        // 如果查找到的政策不为空，则输出 "已找到"
        if (policies != null && !policies.isEmpty()) {
            System.out.println("已找到");
        }

        return policies;
    }
    
}

