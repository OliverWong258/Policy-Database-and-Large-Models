package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PolicyMapper {

    /**
     * 添加数据
     */
    @Insert("INSERT INTO Policies (type, date, day_of_the_week, agency, subagency, subject, cfr, depdoc, frdoc, bilcod, summary, content) " +
            "VALUES (#{type}, #{date}, #{dayOfWeek}, #{agency}, #{subagency}, #{subjectJson}, #{cfr}, #{depdoc}, #{frdoc}, #{bilcod}, #{summary}, #{content})")
    void insertDocument(
            @Param("type") String type,
            @Param("date") String date,
            @Param("dayOfWeek") String dayOfWeek,
            @Param("agency") String agency,
            @Param("subagency") String subagency,
            @Param("subjectJson") String subjectJson,
            @Param("cfr") String cfr,
            @Param("depdoc") String depdoc,
            @Param("frdoc") String frdoc,
            @Param("bilcod") String bilcod,
            @Param("summary") String summary,
            @Param("content") String content
    );
}