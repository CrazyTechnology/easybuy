package com.easybuy.ming.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016-09-27.
 */
public class PoiExcelExport {
    HttpServletResponse response;
    // 文件名
    private String fileName;
    // 文件保存路径
    private String fileDir;
    // sheet名
    private String sheetName;
    // 表头字体
    private String titleFontType = "Arial Unicode MS";
    // 表头背景色
    private String titleBackColor = "C1FBEE";
    // 表头字号
    private short titleFontSize = 12;
    // 添加自动筛选的列 如 A:M
    private String address = "";
    // 正文字体
    private String contentFontType = "Arial Unicode MS";
    // 正文字号
    private short contentFontSize = 12;
    // Float类型数据小数位
    private String floatDecimal = ".00";
    // Double类型数据小数位
    private String doubleDecimal = ".00";
    // 设置列的公式
    private String colFormula[] = null;

    DecimalFormat floatDecimalFormat = new DecimalFormat(floatDecimal);
    DecimalFormat doubleDecimalFormat = new DecimalFormat(doubleDecimal);

    private HSSFWorkbook workbook = null;

    public PoiExcelExport(String fileDir, String sheetName) {
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new HSSFWorkbook();
    }

    public PoiExcelExport(HttpServletResponse response, String fileName, String sheetName) {
        this.response = response;
        this.fileName = fileName;
        this.sheetName = sheetName;
        workbook = new HSSFWorkbook();
    }

    /**
     * 设置表头字体.
     *
     * @param titleFontType
     */
    public void setTitleFontType(String titleFontType) {
        this.titleFontType = titleFontType;
    }

    /**
     * 设置表头背景色.
     *
     * @param titleBackColor 十六进制
     */
    public void setTitleBackColor(String titleBackColor) {
        this.titleBackColor = titleBackColor;
    }

    /**
     * 设置表头字体大小.
     *
     * @param titleFontSize
     */
    public void setTitleFontSize(short titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    /**
     * 设置表头自动筛选栏位,如A:AC.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 设置正文字体.
     *
     * @param contentFontType
     */
    public void setContentFontType(String contentFontType) {
        this.contentFontType = contentFontType;
    }

    /**
     * 设置正文字号.
     *
     * @param contentFontSize
     */
    public void setContentFontSize(short contentFontSize) {
        this.contentFontSize = contentFontSize;
    }

    /**
     * 设置float类型数据小数位 默认.00
     *
     * @param doubleDecimal 如 ".00"
     */
    public void setDoubleDecimal(String doubleDecimal) {
        this.doubleDecimal = doubleDecimal;
    }

    /**
     * 设置doubel类型数据小数位 默认.00
     *
     * @param floatDecimalFormat 如 ".00
     */
    public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
        this.floatDecimalFormat = floatDecimalFormat;
    }

    /**
     * 设置列的公式
     *
     * @param colFormula 存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
     */
    public void setColFormula(String[] colFormula) {
        this.colFormula = colFormula;
    }

    /**
     * 写excel.
     *
     * @param titleColumn 对应bean的属性名
     * @param titleName   excel要导出的表名
     * @param titleSize   列宽
     * @param dataList    数据
     */
    @SuppressWarnings({"unchecked", "rawtypes", "unused"})
    public void wirteExcel(String titleColumn[], String titleName[], int titleSize[], List<?> dataList) {
        // 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        Sheet sheet = workbook.createSheet(this.sheetName);
        // 新建文件
        OutputStream out = null;
        try {
            if (fileDir != null && fileDir != "") {
                // 有文件路径
                out = new FileOutputStream(fileDir);
            } else {
                // 否则，直接写到输出流中
                out = response.getOutputStream();
                fileName = fileName + ".xls";
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            }

            // 写入excel的表头
            Row titleNameRow = workbook.getSheet(sheetName).createRow(0);
            // 设置样式
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle = (HSSFCellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
            titleStyle = (HSSFCellStyle) setColor(titleStyle, titleBackColor, (short) 10);

            for (int i = 0; i < titleName.length; i++) {
                sheet.setColumnWidth(i, titleSize[i] * 256); // 设置宽度
                Cell cell = titleNameRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }

            // 为表头添加自动筛选
            if (!"".equals(address)) {
                CellRangeAddress c = (CellRangeAddress) CellRangeAddress.valueOf(address);
                sheet.setAutoFilter(c);
            }

            // 通过反射获取数据并写入到excel中
            if (dataList != null && dataList.size() > 0) {
                // 设置样式
                HSSFCellStyle dataStyle = workbook.createCellStyle();
                titleStyle = (HSSFCellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);

                if (titleColumn.length > 0) {
                    for (int rowIndex = 1; rowIndex <= dataList.size(); rowIndex++) {
                        Object obj = dataList.get(rowIndex - 1); // 获得该对象
                        // 判断列表是否是map
                        if (obj.getClass() == HashMap.class) {

                            Class clsss = obj.getClass();
                            Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                            for (int columnIndex = 0; columnIndex < titleColumn.length; columnIndex++) {
                                String title = titleColumn[columnIndex].toString().trim();
                                if (!"".equals(title)) { // 字段不为空
                                    String methodName = "get(\"" + title + "\")";
                                    String data = (String) ((HashMap) obj).get(title).toString();

                                    Cell cell = dataRow.createCell(columnIndex);
                                    if (data != null && !"".equals(data)) {

                                        cell.setCellValue(data);

                                    }
                                } else { // 字段为空 检查该列是否是公式
                                    if (colFormula != null) {
                                        String sixBuf = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
                                        Cell cell = dataRow.createCell(columnIndex);
                                        cell.setCellFormula(sixBuf.toString());
                                    }
                                }
                            }

                        } else {
                            Class clsss = obj.getClass(); // 获得该对对象的class实例
                            Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                            for (int columnIndex = 0; columnIndex < titleColumn.length; columnIndex++) {
                                String title = titleColumn[columnIndex].toString().trim();
                                if (!"".equals(title)) { // 字段不为空
                                    // 使首字母大写
                                    String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1, title.length()); // 使其首字母大写;
                                    String methodName = "get" + UTitle;

                                    // 设置要执行的方法
                                    Method method = clsss.getDeclaredMethod(methodName);

                                    // 获取返回类型
                                    String returnType = method.getReturnType().getName();

                                    String data = method.invoke(obj) == null ? "" : method.invoke(obj).toString();
                                    Cell cell = dataRow.createCell(columnIndex);
                                    if (data != null && !"".equals(data)) {
                                        if ("int".equals(returnType)) {
                                            cell.setCellValue(Integer.parseInt(data));
                                        } else if ("long".equals(returnType)) {
                                            cell.setCellValue(Long.parseLong(data));
                                        } else if ("float".equals(returnType)) {
                                            cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
                                        } else if ("double".equals(returnType)) {
                                            cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                                        } else {
                                            cell.setCellValue(data);
                                        }
                                    }
                                } else { // 字段为空 检查该列是否是公式
                                    if (colFormula != null) {
                                        String sixBuf = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
                                        Cell cell = dataRow.createCell(columnIndex);
                                        cell.setCellFormula(sixBuf.toString());
                                    }
                                }
                            }

                        }

                        ////
                    }

                }
            }

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将16进制的颜色代码写入样式中来设置颜色
     *
     * @param style 保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public CellStyle setColor(CellStyle style, String color, short index) {
        if (color != "" && color != null) {
            // 转为RGB码
            int r = Integer.parseInt((color.substring(0, 2)), 16); // 转为16进制
            int g = Integer.parseInt((color.substring(2, 4)), 16);
            int b = Integer.parseInt((color.substring(4, 6)), 16);
            // 自定义cell颜色
            HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex((short) index, (byte) r, (byte) g, (byte) b);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(index);
        }
        return style;
    }

    /**
     * 设置字体并加外边框
     *
     * @param style 样式
     * @param style 字体名
     * @param style 大小
     * @return
     */
    public CellStyle setFontAndBorder(CellStyle style, String fontName, short size) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        // font.setBold(true);
        style.setFont(font);
        // 水平对齐方式
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 删除文件
     *
     * @param
     * @return
     */
    public boolean deleteExcel() {
        boolean flag = false;
        File file = new File(this.fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param
     * @return
     */
    public boolean deleteExcel(String path) {
        boolean flag = false;
        File file = new File(path);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        /*
		 * String bookName = "第一个导出"; // 字段名称 String[] cellTitle = {
		 * "序号序号序号序号序号序号序号序号序号序号序号", "姓名", "性别", "年龄" }; // 字段名称 String[]
		 * cellValue = { "序号序号序号序号序号序号序号序号序号序号序号", "姓名", "性别", "年龄" };
		 * List<HashMap<String, Object>> dataList = null; try {
		 * Export2007(bookName, cellTitle, cellValue, dataList); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
    }

    public static void Export2007(String bookName, String titleColumn[], String titleName[], List<HashMap<String, Object>> dataList, HttpServletResponse response) throws IOException {

        // 创建HSSFWorkbook对象
        XSSFWorkbook workBook = new XSSFWorkbook();// 创建工作薄
        // 创建HSSFSheet对象
        XSSFSheet sheet = workBook.createSheet();
        // 设置第一个sheet的名称
        workBook.setSheetName(0, "sheet");// 工作簿名称

        XSSFFont font = workBook.createFont();
        font.setColor(XSSFFont.COLOR_NORMAL);
        font.setBold(true);
        XSSFCellStyle cellStyle = getCellStyle(font, workBook);

        // 合并标题列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleName.length - 1));
        XSSFCell titleCell = sheet.createRow((short) 0).createCell(0, CellType.NUMERIC);
        titleCell.setCellStyle(cellStyle);
        titleCell.setCellType(CellType.STRING);
        titleCell.setCellValue(bookName);

        // 设置列标题
        XSSFRow titleRow = sheet.createRow((short) 1);
        for (int n = 0, size = titleName.length; n < size; n++) {// 创建第1行标题单元格
            // 自动设置列宽
            sheet.setColumnWidth(n, titleName[n].getBytes().length * 1 * 256);
            XSSFCell cell = titleRow.createCell(n, CellType.NUMERIC);
            // 设置单元格样式
            cell.setCellStyle(cellStyle);
            // 设置单元格数据类型
            cell.setCellType(CellType.STRING);
            // 设置单元格内容
            cell.setCellValue(titleName[n]);
        }

        if (dataList != null && dataList.size() > 0) {

            XSSFFont font1 = workBook.createFont();
            XSSFCellStyle cellStyle1 = getCellStyle(font1, workBook);
            for (int i = 0; i < dataList.size(); i++) {
                XSSFRow row = sheet.createRow((short) i + 2);
                for (int j = 0, length = titleColumn.length; j < length; j++) {
                    XSSFCell cell = row.createCell(j);
                    // 设置单元格样式
                    cell.setCellStyle(cellStyle1);

                    String str = (String) dataList.get(i).get(titleColumn[j]);
                    int lengths = str.length();
                    lengths = lengths < 10 ? 10 : lengths;
                    if ("0000-00-00 00:00".equals(str) || "0000-00-00".equals(str) || "".equals(str))
                        str = "-";
                    else
                        sheet.setColumnWidth(j, lengths * 1 * 256);
                    // 设置单元格数据类型
                    cell.setCellType(CellType.STRING);
                    // 设置单元格内容
                    cell.setCellValue(str);
                }
            }
        }

        // 表示以附件的形式把文件发送到客户端
        // 通过response的输出流把工作薄的流发送浏览器形成文件
        OutputStream outStream = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(bookName + ".xlsx", "UTF-8"));
        response.setCharacterEncoding("utf-8");
        workBook.write(outStream);
        outStream.flush();
        outStream.close();
		/*
		 * String exportPath = getExportPath(request); String filePath =
		 * exportPath + bookName+".xlsx"; downFile(filePath, request, response);
		 */
    }

    public static XSSFCellStyle getCellStyle(XSSFFont font, XSSFWorkbook workBook) {
        XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
        cellStyle.setFont(font);
        // 水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }
}
