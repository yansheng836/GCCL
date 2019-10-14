package xyz.yansheng.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import xyz.yansheng.bean.Category;

/**
 * 文件工具类，作用：将爬取分类专栏的博客的数据以特定的格式写到文件中。
 * 
 * @author yansheng
 * @date 2019/10/12
 */
public class FileUtil {

    /**
     * 生成博客目录的列表文件（markdown格式）。遍历分类专栏，将每个分类专栏下的博客进行格式化输出，用StringBuffer进行拼接，最后转为String，写到文件中。
     * 
     * @param pathname
     *            文件名
     * @param categoryList
     *            分类专栏列表
     * @return 成功返回true,失败返回false
     */
    public static boolean generateCsdnList(String pathname, ArrayList<Category> categoryList) {

        // 1.定义一个StringBuffer变量，用于拼接博客的输出信息。
        StringBuffer stringBuffer = new StringBuffer(5000);
        // 为CSDN的markdown设置头部信息（该头部信息可以在CSDN中生成文章的标题导航目录）
        String prefix = "@[TOC](博客目录)\n\n---\n\n";
        stringBuffer.append(prefix);

        // 2.获得每个分类专栏的所有文章信息
        for (Category category : categoryList) {
            // 将分类的内容转化为markdown类型字符串，添加到stringBuffer
            stringBuffer.append(category.toStringMd());
            stringBuffer.append("\n");
        }

        // 3.将分类列表写到文件中
        String data = new String(stringBuffer);
        // System.out.println("\n详细信息如下:");
        // System.out.println("-----------------------------------------------------------------\n"
        // + data);

        File file = new File(pathname);
        try {
            // 设置编码为utf8
            FileUtils.writeStringToFile(file, data, SpiderUtil.charsetName);
        } catch (IOException e) {
            System.err.println("----生成'博客导航分类目录'文件时，发生异常！！");
            return false;
        }

        return true;
    }

    /**
     * 生成当时的格式化的时间，用于拼接生成文件的文件名。
     * 
     * @return 格式化的时间字符串
     */
    public static String getDateString() {

        Date date = new Date();
        // 设置时间的显示格式
        String pattern = "yyyy-MM-dd.HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(date);

        return dateString;
    }

    /**
     * 将毫秒转为'需要的时间'，取决于毫秒转化后比较接近哪个单位（秒、分钟）。
     * 
     * @param time
     *            毫秒数
     * @return 转换的秒的字符串
     */
    public static String getSecondString(long time) {

        // 判断时间是否超过1分钟，如果没有超过显示为秒钟，如2000ms转为：02.000s；如果超过显示分钟60000ms转为：01.00m
        Long oneMinute = 60000L;
        // 设置时间的显示格式
        String pattern = null;
        boolean isMoreThenOneMinute = false;
        if (time < oneMinute) {
            pattern = "s.SSS";
        } else {
            pattern = "m.ss";
            isMoreThenOneMinute = true;
        }

        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(date);

        // return dateString;
        return !isMoreThenOneMinute ? (dateString + "秒") : (dateString + "分钟");
    }

    /**
     * 打印ASCII的Goodbye，用于程序结尾。（来源：http://www.network-science.de/ascii/ ：Font: big Reflection: no
     * Adjustment: left Stretch: no Width: 80 Text: Goodbye!）
     * 
     * @return
     */
    public static String sayGoodbye() {

        String goodbye = "  _____                 _ _                _ \n"
            + " / ____|               | | |              | |\n"
            + "| |  __  ___   ___   __| | |__  _   _  ___| |\n"
            + "| | |_ |/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\ |\n"
            + "| |__| | (_) | (_) | (_| | |_) | |_| |  __/_|\n"
            + " \\_____|\\___/ \\___/ \\__,_|_.__/ \\__, |\\___(_)\n"
            + "                                 __/ |       \n"
            + "                                |___/        ";

        return goodbye;
    }

    /**
     * 打印ASCII的Welcome，用于程序开头。
     * 
     * @return
     */
    public static String sayWelcome() {

        String welcome = "__          __  _                          _ \n"
            + "\\ \\        / / | |                        | |\n"
            + " \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___| |\n"
            + "  \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\n"
            + "   \\  /\\  /  __/ | (_| (_) | | | | | |  __/_|\n"
            + "    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)\n"
            + "                                             ";

        return welcome;
    }

}
