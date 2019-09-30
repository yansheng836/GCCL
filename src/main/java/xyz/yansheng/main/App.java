package xyz.yansheng.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import xyz.yansheng.bean.Category;
import xyz.yansheng.util.Utility;

/**
 * 生成CSDN博客分类导航目录。思路：从博客主页获取非空的分类专栏列表，然后到具体的分类专栏页面，获取该分类下的所有博客列表。 
 * 最后利用所有的分类信息生成CSDN的markdown编辑器的markdown格式的文件。
 * 
 * @author yansheng
 * @date 2019/09/30
 */
public class App {
    public static void main(String[] args) {
        // 1.得到用户名
        String username = "weixin_41287260";

        // 2.获取该用户的非空的分类专栏列表
        ArrayList<Category> categoryList = new ArrayList<Category>(20);
        categoryList = Utility.getCategoryList(username);
        // for (Category category : categoryList) {
        // System.out.println(category.toString());
        // }

        // 3.获得每个分类专栏的所有文章信息
        StringBuffer stringBuffer = new StringBuffer(5000);
        // 为CSDN的markdown设置头部信息
        String prefix = "@[TOC](博客目录)\n\n---\n\n";
        stringBuffer.append(prefix);

        for (Category category : categoryList) {
            // 获取该分类的所有博客列表
            category = Utility.getCategoryBlogs(category);
            // System.out.println(category.getBlogs());

            // 将分类的内容转化为markdown类型字符串，添加到stringBuffer
            stringBuffer.append(category.toStringMd());
            stringBuffer.append("\n");
        }

        // 4.将分类列表写到文件中
        String data = new String(stringBuffer);
        System.out.println(data);
        File file = new File("./CSDN博客目录.md");
        try {
            FileUtils.writeStringToFile(file, data);
            System.out.println("\n\n生成博客分类导航目录成功！！");
        } catch (IOException e) {
            System.err.println("\n\n生成博客分类导航目录时，发生异常！！");
            e.printStackTrace();
        }

    }
}
