package cn.hpt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class HelperUtil {

    public static synchronized String createRandomString(int size) {// 随机字符串
        char[] c = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        Random random = new Random(); // 初始化随机数产生器
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[Math.abs(random.nextInt(10)) % c.length]);
        }
        return sb.toString();
    }

    public static synchronized boolean isMatch(String str, String regEx) {
        Pattern ep = Pattern.compile(regEx);
        return ep.matcher(str).matches();
    }

    public static synchronized String format(Object result, String regEx) {
        NumberFormat format = new DecimalFormat(regEx);
        return format.format(result);
    }

    public static synchronized void exportExcel(File outFile, List<Object[]> items) {
        FileOutputStream fOut = null;
        try {
            // 新建一输出文件流
            fOut = new FileOutputStream(outFile);
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("效益指标");
            HSSFSheet sheet = workbook.createSheet("报表分析");
            for (int i = 0; i < items.size(); i++) {
                Object[] values = items.get(i);
                // 在索引0的位置创建行（最顶端的行）
                HSSFRow row = sheet.createRow(i);
                //在索引0的位置创建单元格（左上端）
                for (int j = 0; j < values.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    // 定义单元格为字符串类型
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    // 在单元格中输入一些内容
                    if (values[j] != null && values[j] instanceof String) {
                        cell.setCellValue(new HSSFRichTextString((String) values[j]));
                    } else {
                        cell.setCellValue(new HSSFRichTextString(format(values[j], "0.##")));
                    }
                }
            }

            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();

            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        } finally {
            // 操作结束，关闭文件
            try {
                if (null != fOut) {
                    fOut.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
