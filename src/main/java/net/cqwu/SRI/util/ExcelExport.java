package net.cqwu.SRI.util;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;

public class ExcelExport<T> {

    private final static Logger log = Logger.getLogger(ExcelExport.class);


    // 定义一个excel所容纳的初始数据量(防止数据过多，因为一个excel表格最多只能存65535行记录(excel2003的))，所以这里取40000

    private static final Integer initial_data = 40000;

    // 累计遍历的数量，用来判断是否超过初始数据，如果超过则新建一个sheet

    private int length = 0;

    public void exportExcel(String headerName, Collection<T> dataset, HttpServletResponse response,HttpServletRequest request,String mimeType) {

        try {
            exportExcel(headerName, null,dataset,response,request,"yyyy-MM-dd",mimeType);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     *
     * @param headerName		文件名称
     * @param headers		表格头部信息
     * @param dataset		查到的数据集合
     * @param response		响应response
     * @param request		请求request
     * @param mimeType		content类型
     */
    public void exportExcel(String headerName, String[] headers, Collection<T> dataset,HttpServletResponse response,HttpServletRequest request,String mimeType) {

        try {
            exportExcel(headerName, headers, dataset,response,request,"yyyy-MM-dd",mimeType);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void exportExcel(String title, String[] headers, Collection<T> dataset,HttpServletResponse response,HttpServletRequest request, String pattern,String mimeType)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        // 声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet();

        sheet.autoSizeColumn(1, true);// 自适应列宽度

        HSSFFont font = workbook.createFont();

        font.setFontName("黑体");

        font.setFontHeightInPoints((short)16);

        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);

        }

        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        // 遍历集合数据，产生数据行

        Iterator<T> it = dataset.iterator();

        int index = 0;

        while (it.hasNext()) {

            index++;

            length++;

            row = sheet.createRow(index);

            T t = it.next();

            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

            Field[] fields = t.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {

                HSSFCell cell = row.createCell(i);

                Field field = fields[i];

                String fieldName = field.getName();

                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

//                System.out.println("我执行到了！！！" + getMethodName);

                Class<?> cts = t.getClass();

//                System.out.println("我执行到了" + cts.toString());

                Method getMethod = cts.getMethod(getMethodName);

                Object value = getMethod.invoke(t);

                String textValue = null;

                if (null != value) {

                    if (value instanceof Integer) {

                        int intValue = (Integer) value;

                        cell.setCellValue(intValue);
                    } else if (value instanceof Float) {

                        float fValue = (float) value;

                        textValue = Float.toString(fValue);

                        cell.setCellValue(textValue);
                    } else if (value instanceof Double) {

                        double dValue = (double) value;

                        cell.setCellValue(dValue);
                    } else if (value instanceof Long) {

                        long lValue = (long) value;

                        cell.setCellValue(lValue);
                    } else if (value instanceof byte[]) {

                        byte[] bValue = (byte[]) value;

                        // 有图片时设置行高为60px
                        row.setHeightInPoints(60);

                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
                                index);

                        anchor.setAnchorType(ClientAnchor.AnchorType.byId(2));

                        patriarch.createPicture(anchor, workbook.addPicture(bValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

                    } else {

                        textValue = value.toString();
                    }
                } else {

                    textValue = "";
                }

                if (textValue != null) {

                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");

                    Matcher matcher = p.matcher(textValue);

                    if (matcher.matches()) {

                        // 是数字当做double处理

                        cell.setCellValue(Double.parseDouble(textValue));

                    } else {

                        HSSFRichTextString richString = new HSSFRichTextString(textValue);

                        cell.setCellValue(richString);
                    }
                }
            }

            if (length % initial_data == 0) {

                sheet = workbook.createSheet();

                // 设置表格默认宽度为15个字节
                sheet.setDefaultColumnWidth(15);

                row = sheet.createRow(0);

                for (int i = 0; i < headers.length; i++) {

                    HSSFCell cell = row.createCell(i);

                    HSSFRichTextString text = new HSSFRichTextString(headers[i]);

                    cell.setCellValue(text);

                    index = 0;
                }
            }
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //8.获取浏览器信息,对文件名进行重新编码
            String fileName = URLEncoder.encode(title, "UTF-8");
            //9.设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            workbook.write(outputStream);
        } catch (IOException e) {


            log.error(ExceptionUtils.getStackTrace(e));

            log.error("导出数据失败！！");
        }

    }
}
