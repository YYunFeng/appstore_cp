package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.json.ExportExcelJSON;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.manager.cp.DataCenterManager;
import com.jd.appstore.service.cp.DataCenterService;
import com.jd.common.util.PaginatedList;
import com.jd.common.web.result.Result;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:08
 * To change this template use File | Settings | File Templates.
 */
public class DataCenterServiceImpl implements DataCenterService {
    private DataCenterManager dataCenterManager;
    private String excelPath;
    private String downloadPath;

    public Result downStauts(StatusDownload statusDownload, int pageIndex, int pageSize) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date()); //设置当前日期
        calendar.add(Calendar.DATE, -7);//天数减7
        statusDownload.setBeginTime(df.format(calendar.getTime()));
        statusDownload.setEndTime(df.format(new Date()));
        Result result = new Result();
        result.addDefaultModel("statusDownload", dataCenterManager.downStauts(statusDownload, pageIndex, pageSize));
        return result;
    }

    public Result statAppReuslt(StatAppDown statAppDown) {
        Result result = new Result();
        try {
            result.addDefaultModel("statAppReuslt", dataCenterManager.statAppReuslt(statAppDown));
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public Result getCountApps(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        Result result = new Result();
        try {
            countAppParameter.setFlag(0);
            result.addDefaultModel("countApps", dataCenterManager.getCountApps(countAppParameter, pageIndex, pageSize));
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result countAppsPhoneImei(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        Result result = new Result();
        try {
            countAppParameter.setFlag(0);
            result.addDefaultModel("countAppsPhoneImei", dataCenterManager.countAppsPhoneImei(countAppParameter, pageIndex, pageSize));
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result getPhoneImeiFromInstall(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        Result result = new Result();
        try {
            countAppParameter.setFlag(0);
            result.addDefaultModel("countAppsPhoneImei", dataCenterManager.getPhoneImeiFromInstall(countAppParameter, pageIndex, pageSize));
            result.addDefaultModel("appName", countAppParameter.getAppName());
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ExportExcelJSON exportExcel(CountAppParameter countAppParameter, Integer flag) {
        ExportExcelJSON exportExcelJSON = new ExportExcelJSON();
        try {
            // 创建工作书册
            short height = 400;
            XSSFWorkbook wb = new XSSFWorkbook();
            // 创建Excel的工作sheet，对应到一个Excel文档中的tab
            XSSFSheet sheet = wb.createSheet("应用安装信息");

            // 创建Excel的sheet的第一行
            XSSFRow row = sheet.createRow(0);
            row.setHeight(height);
            XSSFCell cell = row.createCell(0);
            // 设置字体 --title
            Font fontTitle = wb.createFont();
            fontTitle.setFontHeightInPoints((short) 12);
            fontTitle.setFontName("微软雅黑");
            fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);

            // 设置字体--内容
            Font fontContent = wb.createFont();
            fontContent.setFontHeightInPoints((short) 10);
            fontContent.setFontName("微软雅黑");

            // 单元格样式 --title属性
            XSSFCellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setFont(fontTitle);
            // 91, 155, 213
            titleStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(91, 155, 213)));
            titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);


            // 单元格属性 --内容属性title
            XSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cellStyle.setFont(fontContent);
            cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(180, 198, 231)));
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle.setWrapText(true);

            // 单元格属性 --内容属性content
            XSSFCellStyle cellStyleContent = wb.createCellStyle();
            cellStyleContent.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleContent.setFont(fontContent);

            if (flag != null) {
                //  应用安装信息
                if (flag.intValue() == 0) {
                    PaginatedList<CountAppResult> countAppResultPaginatedList = dataCenterManager.getCountApps(countAppParameter, 0, 0);
                    if (countAppResultPaginatedList != null && countAppResultPaginatedList.size() > 0) {
                        // 设置每一列
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.setColumnWidth(2, 5000);
                        //合并低一列的第一行到第五行
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
                        // 设置第一行的值
                        if (countAppParameter.getStartTime() == null || countAppParameter.getStartTime() == null || countAppParameter.getStartTime().equals("") || countAppParameter.getEndTime().equals("")) {
                            cell.setCellValue("应用安装信息");
                        } else {
                            cell.setCellValue("应用安装信息(" + countAppParameter.getStartTime() + "至" + countAppParameter.getEndTime() + ")");
                        }
                        // 设置第一行的属性
                        cell.setCellStyle(titleStyle);

                        // 创建Excel的sheet的第二行第一列
                        XSSFRow row1 = sheet.createRow(1);
                        row1.setHeight(height);
                        XSSFCell cell1 = row1.createCell(0);
                        cell1.setCellValue("应用名称");
                        cell1.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell2 = row1.createCell(1);
                        cell2.setCellValue("安装量");
                        cell2.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell3 = row1.createCell(2);
                        cell3.setCellValue("到达量");
                        cell3.setCellStyle(cellStyle);
                        //  开始写入数据
                        for (int i = 0; i < countAppResultPaginatedList.size(); i++) {
                            XSSFRow rowStatByApps = sheet.createRow(i + 2);
                            rowStatByApps.setHeight(height);
                            XSSFCell cellValidMachineTotal1 = rowStatByApps.createCell(0);
                            cellValidMachineTotal1.setCellValue(countAppResultPaginatedList.get(i).getAppName());
                            cellValidMachineTotal1.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal2 = rowStatByApps.createCell(1);
                            cellValidMachineTotal2.setCellValue(countAppResultPaginatedList.get(i).getInstallCounts());
                            cellValidMachineTotal2.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal3 = rowStatByApps.createCell(2);
                            cellValidMachineTotal3.setCellValue(countAppResultPaginatedList.get(i).getCountApps());
                            cellValidMachineTotal3.setCellStyle(cellStyleContent);
                        }
                    } else {
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
                        cell.setCellValue("暂无安装信息");
                        cell.setCellStyle(cellStyle);
                    }
                    // 导出应用到达的串号
                } else if (flag.intValue() == 1) {
                    PaginatedList<CountAppResult> countAppResultPaginatedList = dataCenterManager.countAppsPhoneImei(countAppParameter, 0, 0);
                    if (countAppResultPaginatedList != null && countAppResultPaginatedList.size() > 0) {
                        // 设置每一列
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.setColumnWidth(2, 5000);
                        sheet.setColumnWidth(3, 5000);
                        //合并低一列的第一行到第五行
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
                        // 设置第一行的值
                        if (countAppParameter.getStartTime() == null || countAppParameter.getStartTime() == null || countAppParameter.getStartTime().equals("") || countAppParameter.getEndTime().equals("")) {
                            cell.setCellValue(countAppParameter.getAppName() + "到达的手机串号");
                        } else {
                            cell.setCellValue(countAppParameter.getAppName() + "到达的手机串号(" + countAppParameter.getStartTime() + "至" + countAppParameter.getEndTime() + ")");
                        }
                        // 设置第一行的属性
                        cell.setCellStyle(titleStyle);

                        // 创建Excel的sheet的第二行第一列
                        XSSFRow row1 = sheet.createRow(1);
                        row1.setHeight(height);
                        XSSFCell cell1 = row1.createCell(0);
                        cell1.setCellValue("应用名称");
                        cell1.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell2 = row1.createCell(1);
                        cell2.setCellValue("串号");
                        cell2.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell3 = row1.createCell(2);
                        cell3.setCellValue("手机型号");
                        cell3.setCellStyle(cellStyle);


                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell4 = row1.createCell(3);
                        cell4.setCellValue("安装时间");
                        cell4.setCellStyle(cellStyle);

                        //  开始写入数据
                        for (int i = 0; i < countAppResultPaginatedList.size(); i++) {
                            XSSFRow rowStatByApps = sheet.createRow(i + 2);
                            rowStatByApps.setHeight(height);
                            XSSFCell cellValidMachineTotal1 = rowStatByApps.createCell(0);
                            cellValidMachineTotal1.setCellValue(countAppResultPaginatedList.get(i).getAppName());
                            cellValidMachineTotal1.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal2 = rowStatByApps.createCell(1);
                            cellValidMachineTotal2.setCellValue(countAppResultPaginatedList.get(i).getPhoneImei());
                            cellValidMachineTotal2.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal3 = rowStatByApps.createCell(2);
                            cellValidMachineTotal3.setCellValue(countAppResultPaginatedList.get(i).getPhoneModelName());
                            cellValidMachineTotal3.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal4 = rowStatByApps.createCell(3);
                            cellValidMachineTotal4.setCellValue(countAppResultPaginatedList.get(i).getInstallTime());
                            cellValidMachineTotal4.setCellStyle(cellStyleContent);
                        }
                    } else {
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
                        cell.setCellValue(countAppParameter.getAppName() + "无到达手机串号");
                        cell.setCellStyle(cellStyle);
                    }
                    //  导出安装的手机串号
                } else if (flag.intValue() == 2) {
                    PaginatedList<CountAppResult> countAppResultPaginatedList = dataCenterManager.getPhoneImeiFromInstall(countAppParameter, 0, 0);
                    if (countAppResultPaginatedList != null && countAppResultPaginatedList.size() > 0) {
                        // 设置每一列
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.setColumnWidth(2, 5000);
                        sheet.setColumnWidth(3, 5000);
                        //合并低一列的第一行到第五行
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
                        // 设置第一行的值
                        if (countAppParameter.getStartTime() == null || countAppParameter.getStartTime() == null || countAppParameter.getStartTime().equals("") || countAppParameter.getEndTime().equals("")) {
                            cell.setCellValue("安装" + countAppParameter.getAppName() + "的手机串号");
                        } else {
                            cell.setCellValue("安装" + countAppParameter.getAppName() + "的手机串号(" + countAppParameter.getStartTime() + "至" + countAppParameter.getEndTime() + ")");
                        }

                        // 设置第一行的属性
                        cell.setCellStyle(titleStyle);

                        // 创建Excel的sheet的第二行第一列
                        XSSFRow row1 = sheet.createRow(1);
                        row1.setHeight(height);
                        XSSFCell cell1 = row1.createCell(0);
                        cell1.setCellValue("应用名称");
                        cell1.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell2 = row1.createCell(1);
                        cell2.setCellValue("串号");
                        cell2.setCellStyle(cellStyle);

                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell3 = row1.createCell(2);
                        cell3.setCellValue("手机型号");
                        cell3.setCellStyle(cellStyle);


                        // 创建Excel的sheet的第二行第二列
                        XSSFCell cell4 = row1.createCell(3);
                        cell4.setCellValue("安装时间");
                        cell4.setCellStyle(cellStyle);


                        //  开始写入数据
                        for (int i = 0; i < countAppResultPaginatedList.size(); i++) {
                            XSSFRow rowStatByApps = sheet.createRow(i + 2);
                            rowStatByApps.setHeight(height);
                            XSSFCell cellValidMachineTotal1 = rowStatByApps.createCell(0);
                            cellValidMachineTotal1.setCellValue(countAppParameter.getAppName());
                            cellValidMachineTotal1.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal2 = rowStatByApps.createCell(1);
                            cellValidMachineTotal2.setCellValue(countAppResultPaginatedList.get(i).getPhoneImei());
                            cellValidMachineTotal2.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal3 = rowStatByApps.createCell(2);
                            cellValidMachineTotal3.setCellValue(countAppResultPaginatedList.get(i).getPhoneModelName());
                            cellValidMachineTotal3.setCellStyle(cellStyleContent);

                            XSSFCell cellValidMachineTotal4 = rowStatByApps.createCell(3);
                            cellValidMachineTotal4.setCellValue(countAppResultPaginatedList.get(i).getInstallTime());
                            cellValidMachineTotal4.setCellStyle(cellStyleContent);
                        }
                    } else {
                        sheet.setColumnWidth(0, 5000);
                        sheet.setColumnWidth(1, 5000);
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
                        cell.setCellValue(countAppParameter.getAppName() + "无安装手机串号");
                        cell.setCellStyle(cellStyle);
                    }
                }
            }

            String newPath = System.currentTimeMillis() + ".xlsx";
            File installLogExcel = new File(excelPath);
            if (!installLogExcel.exists()) {
                installLogExcel.mkdirs();
            }
            try {
                FileOutputStream os = new FileOutputStream(excelPath + "/" + newPath);
                wb.write(os);
                os.close();
                exportExcelJSON.setStatus(1);
                exportExcelJSON.setExcelDownAddress(downloadPath + "cp_app_installmess_excel/" + newPath);
            } catch (Exception e) {
                exportExcelJSON.setStatus(0);
                e.printStackTrace();
            }
        } catch (Exception e) {
            exportExcelJSON.setStatus(0);
            e.printStackTrace();
        }
        return exportExcelJSON;
    }


    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public void setDataCenterManager(DataCenterManager dataCenterManager) {
        this.dataCenterManager = dataCenterManager;
    }
}
