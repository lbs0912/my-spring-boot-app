package com.lbs.spring.my_app.controller;


import com.lbs.spring.my_app.utils.FileExportUtils;

import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出数据至文件 - .csv .xlsx
 *
 * @author lbs
 */
@RestController
public class ExportDataToFileController {
    @GetMapping("exportData/csv")
    public void exportDataToCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = this.getFileName(request, "测试数据.csv");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

        LinkedHashMap<String, Object> header = new LinkedHashMap<>();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        header.put("1", "姓名");
        header.put("2", "年龄");
        List<LinkedHashMap<String, Object>> data = new ArrayList<>();
        body.put("1", "小明");
        body.put("2", Math.ceil(1 + 10 * Math.random()));
        data.add(header);
        data.add(body);
        data.add(body);
        data.add(body);
        FileCopyUtils.copy(FileExportUtils.exportCSV(data), response.getOutputStream());
    }

    @GetMapping("exportData/xlsx")
    public void exportDataToXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = this.getFileName(request, "测试数据.xlsx");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

        List<LinkedHashMap<String, Object>> datas = new ArrayList<>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("1", "姓名");
        data.put("2", "年龄");
        datas.add(data);
        for (int i = 0; i < 5; i++) {
            data = new LinkedHashMap<>();
            data.put("1", "小青");
            data.put("2", Math.ceil(1 + 10 * Math.random()));
            datas.add(data);
        }

        Map<String, List<LinkedHashMap<String, Object>>> tableData = new HashMap<>();
        tableData.put("日报表", datas);
        tableData.put("周报表", datas);
        tableData.put("月报表", datas);

        FileCopyUtils.copy(FileExportUtils.exportXlsx(tableData), response.getOutputStream());
    }


    /**
     * 根据UA设置文件名的编码，防止出现中文乱码
     *
     * @param request request
     * @param name    name
     * @return String
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    private String getFileName(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("USER-AGENT");
        return userAgent.contains("Mozilla") ? new String(name.getBytes(), "ISO8859-1") : name;
    }
}
