package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

import com.example.demo.entity.Policy;
import com.example.demo.service.PolicyService;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    /**
     * 根据查询条件获取政策列表
     * @param keywords 关键词
     * @param department 颁布部门
     * @param publishDate 发布日期
     * @return 政策列表
     */
    @GetMapping("/search")
    public List<Policy> searchPolicies(
            @RequestParam(required = false) String keywords,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) {
        // 调用业务逻辑层方法
        return policyService.searchPolicies(keywords, department, publishDate);
    }

}

