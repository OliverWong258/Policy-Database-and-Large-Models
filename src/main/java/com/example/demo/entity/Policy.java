package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
    private Integer id;               // 政策ID
    private String type;              // 类型
    private String date;           // 日期
    private String dayOfTheWeek;      // 星期几
    private String agency;            // 机构
    private String subagency;         // 子机构
    private String subjectJson;           // 主题（JSON 格式）
    private String cfr;               // CFR
    private String depdoc;            // DEP DOC
    private String frdoc;             // FR DOC
    private String bilcod;            // BIL COD
    private String summary;           // 摘要
    private String content;           // 内容

    @Override
    public String toString() {
        return "Policy{" +
                //"type='" + type + '\'' +
                ", date='" + date + '\'' +
                //", dayOfWeek='" + dayOfTheWeek + '\'' +
                ", agency='" + agency + '\'' +
                //", subagency='" + subagency + '\'' +
                //", subject='" + subjectJson + '\'' +
                //", cfr='" + cfr + '\'' +
                //", depdoc='" + depdoc + '\'' +
                //", frdoc='" + frdoc + '\'' +
                //", bilcod='" + bilcod + '\'' +
                ", summary='" + summary + '\'' +
                //", content='" + content + '\'' +
                '}';
    }
}