package com.example.demo.util;

import com.example.demo.entity.Policy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class XMLExtractor {
    private File inputFile;
    private static XMLExtractor instance;

    private XMLExtractor(){
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized XMLExtractor getInstance(String inputFilePath) {
        if (instance == null) {
            instance = new XMLExtractor();
        }
        instance.inputFile = new File(inputFilePath);
        return instance;
    }

    private static String[] parseDate(String dateStr) throws Exception {// 提取日期的函数
        String[] dateParts = dateStr.split(", ");
        String dayOfWeek = dateParts[0];
        String dateFormatted = dateParts[1] + ", " + dateParts[2];

        // 将月份名称转换为数字格式以适应DATE格式
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("January", "01");
        monthMap.put("February", "02");
        monthMap.put("March", "03");
        monthMap.put("April", "04");
        monthMap.put("May", "05");
        monthMap.put("June", "06");
        monthMap.put("July", "07");
        monthMap.put("August", "08");
        monthMap.put("September", "09");
        monthMap.put("October", "10");
        monthMap.put("November", "11");
        monthMap.put("December", "12");

        for (Map.Entry<String, String> entry : monthMap.entrySet()) {
            dateFormatted = dateFormatted.replace(entry.getKey(), entry.getValue());
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("MM dd, yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inputFormat.parse(dateFormatted);
        String dateSQL = outputFormat.format(date);

        return new String[]{dayOfWeek, dateSQL};
    }

    private static String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
    
    private List<Policy> generatePolicies(String type, NodeList nodeList, String date, String dayOfWeek, Set<String> validAgencies){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element parentElement = (Element) nodeList.item(i);// 获取全文信息
            NodeList items = parentElement.getElementsByTagName(type.toUpperCase());
            //System.out.println(items.getLength());

            for (int j = 0; j < items.getLength(); j++) {
                //遍历<RULES>中的每一个<RULE>
                Element element = (Element) items.item(j);
                String frdoc = getElementTextContent(element, "FRDOC");
                String bilcod = getElementTextContent(element, "BILCOD");// 获取信息
                Element preamb = (Element) element.getElementsByTagName("PREAMB").item(0);

                String agency = getElementTextContent(preamb, "AGENCY");
                String subagency = getElementTextContent(preamb, "SUBAGY");// 获取机构
                
                if (agency != null) agency = agency.toLowerCase();
                if (subagency != null) subagency = subagency.toLowerCase();

                if (validAgencies.contains(agency) || validAgencies.contains(subagency)) {
                    // 仅选择四个部门的信息，若希望先查看完整情况，请注释此行
                    String subjectText = getElementTextContent(preamb, "SUBJECT");
                    String[] subjects = subjectText != null ? subjectText.split("[,;]") : new String[0];
                    JSONArray subjectJson = new JSONArray(subjects);

                    String cfr = getElementTextContent(preamb, "CFR");
                    String depdoc = getElementTextContent(preamb, "DEPDOC");
                    
                    if (depdoc != null && depdoc.length() > 255) {
                        depdoc = depdoc.substring(0, 255); 
                    }// 该函数用于截断 depdoc 字段的值，防止其过长报错，可根据实际情况调整

                    Element sumElement = (Element) element.getElementsByTagName("SUM").item(0);
                    String summary = "";
                    if (sumElement != null) {
                        NodeList summaryNodes = sumElement.getElementsByTagName("P");
                        StringBuilder summaryBuilder = new StringBuilder();
                        for (int k = 0; k < summaryNodes.getLength(); k++) {
                            summaryBuilder.append(summaryNodes.item(k).getTextContent()).append(" ");
                        }
                        summary = summaryBuilder.toString().trim();
                    }// 获取摘要

                    pstmt.setString(1, type);
                    pstmt.setString(2, date);
                    pstmt.setString(3, dayOfWeek);
                    pstmt.setString(4, agency != null ? agency : "0");
                    pstmt.setString(5, subagency != null ? subagency : "0");
                    pstmt.setString(6, subjectJson.length() > 0 ? subjectJson.toString() : "0");
                    pstmt.setString(7, cfr != null ? cfr : "0");
                    pstmt.setString(8, depdoc != null ? depdoc : "0");
                    pstmt.setString(9, frdoc != null ? frdoc : "0");
                    pstmt.setString(10, bilcod != null ? bilcod : "0");
                    pstmt.setString(11, summary.length() > 0 ? summary : "0");
                    pstmt.setString(12, nodeToString(element));
                    pstmt.executeUpdate();
                }
            }
        }
    }

    public List<Policy> extractPolicy(){
        try {
            // 解析XML文件
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 进入<FEDREG>标签（大标签）
            Element fedreg = (Element) doc.getElementsByTagName("FEDREG").item(0);

            // 提取日期和星期几
            String dateStr = fedreg.getElementsByTagName("DATE").item(0).getTextContent();
            String[] dateInfo = parseDate(dateStr);
            String dayOfWeek = dateInfo[0];
            String dateSQL = dateInfo[1];

            // 提取rules, prorules, Notices部分
            NodeList rulesList = fedreg.getElementsByTagName("RULES");
            NodeList prorulesList = fedreg.getElementsByTagName("PRORULES");
            NodeList noticesList = fedreg.getElementsByTagName("NOTICES");



        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}