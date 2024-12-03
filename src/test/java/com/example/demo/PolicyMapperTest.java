package com.example.demo;

import com.example.demo.entity.Policy;
import com.example.demo.mapper.PolicyMapper;
import com.example.demo.util.XMLExtractor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = "/schema.sql")  // 加载 schema.sql 脚本
public class PolicyMapperTest {

    @Autowired
    private PolicyMapper policyMapper;

    @Test
    //@Transactional  // 确保测试后数据不会保存在数据库中
    public void testInsertPolicies() {
        
        String xmlPath = "D:\\Download\\FR-2024\\01\\FR-2024-01-02.xml";
        XMLExtractor extractor = XMLExtractor.getInstance(xmlPath);
        List<Policy> policies = extractor.extractPolicy();

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
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(policy.getDate());
            }
        }
    }
}

