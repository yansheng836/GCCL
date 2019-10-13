package xyz.yansheng.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import xyz.yansheng.bean.Category;

/**
 * @author yansheng
 * @date 2019/10/12
 */
public class FileUtil {

    /**
     * 生成博客目录的列表文件（markdown格式）。遍历分类专栏，将每个分类专栏下的博客进行格式化输出，用StringBuffer进行拼接，最后转为String，写到文件中。
     * 
     * @param pathname
     * @param categoryList
     */
    public static void generateCsdnList(String pathname, ArrayList<Category> categoryList) {

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
//        System.out.println("\n\n 详细信息如下："+pathname + ":\n" + data);
        System.out.println("\n详细信息如下:");
        System.out.println("-----------------------------------------------------------------\n" + data);
        File file = new File(pathname);
        try {
            FileUtils.writeStringToFile(file, data, SpiderUtil.charsetName);
            System.out.println("生成博客分类导航目录成功！！文件路径为：" + pathname);
        } catch (IOException e) {
            System.err.println("生成博客分类导航目录时，发生异常！！");
            e.printStackTrace();
        }

    }

    /**
     * 生成格式化的时间，用于拼接生成文件的文件名。
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

}
