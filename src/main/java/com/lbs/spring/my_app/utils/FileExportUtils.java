package com.lbs.spring.my_app.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件导出实用工具类
 */
public class FileExportUtils {


    /**
     * 导出csv文件
     *
     * @param exportData 要导出的数据集合
     * @return byte[]
     */
    public static byte[] exportCSV(List<LinkedHashMap<String, Object>> exportData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter buffCvsWriter = null;
        try {
            buffCvsWriter = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            // 将body数据写入表格
            for (Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext(); ) {
                fillDataToCsv(buffCvsWriter, iterator.next());
                if (iterator.hasNext()) {
                    buffCvsWriter.newLine();
                }
            }
            // 刷新缓冲
            buffCvsWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (buffCvsWriter != null) {
                try {
                    buffCvsWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toByteArray();
    }

    public static byte[] exportXlsx(Map<String, List<LinkedHashMap<String, Object>>> tableData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建多个sheet
            for (Map.Entry<String, List<LinkedHashMap<String, Object>>> entry : tableData.entrySet()) {
                fillDataToXlsx(workbook.createSheet(entry.getKey()), entry.getValue());
            }

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    /**
     * 将linkedHashMap中的数据，写入xlsx表格中
     *
     * @param sheet sheet
     * @param data  data
     */
    private static void fillDataToXlsx(HSSFSheet sheet, List<LinkedHashMap<String, Object>> data) {
        HSSFRow currRow;
        HSSFCell cell;
        LinkedHashMap row;
        Map.Entry propertyEntry;
        int rowIndex = 0;
        int cellIndex = 0;
        for (Iterator<LinkedHashMap<String, Object>> iterator = data.iterator(); iterator.hasNext(); ) {
            row = iterator.next();
            currRow = sheet.createRow(rowIndex++);
            for (Iterator<Map.Entry> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                propertyEntry = propertyIterator.next();
                if (propertyIterator.hasNext()) {
                    String value = String.valueOf(propertyEntry.getValue());
                    cell = currRow.createCell(cellIndex++);
                    cell.setCellValue(value);
                } else {
                    String value = String.valueOf(propertyEntry.getValue());
                    cell = currRow.createCell(cellIndex++);
                    cell.setCellValue(value);
                    break;
                }
            }
            if (iterator.hasNext()) {
                cellIndex = 0;
            }
        }
    }


    private static void fillDataToCsv(BufferedWriter buffCvsWriter, LinkedHashMap row) throws IOException {
        Map.Entry propertyEntry;
        for (Iterator<Map.Entry> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
            propertyEntry = propertyIterator.next();
            buffCvsWriter.write("\"" + propertyEntry.getValue().toString() + "\"");
            if (propertyIterator.hasNext()) {
                buffCvsWriter.write(",");
            }
        }
    }
}
