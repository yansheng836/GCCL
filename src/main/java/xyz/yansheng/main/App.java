package xyz.yansheng.main;

import java.util.ArrayList;
import java.util.Scanner;

import xyz.yansheng.bean.Blog;
import xyz.yansheng.bean.Category;
import xyz.yansheng.util.FileUtil;
import xyz.yansheng.util.SpiderUtil;

/**
 * 生成CSDN博客分类导航目录。思路：从博客主页获取非空的分类专栏列表，然后到具体的分类专栏页面，获取该分类下的所有博客列表。 最后利用所有的分类信息生成CSDN的markdown编辑器的markdown格式的文件。
 * 
 * @author yansheng
 * @date 2019/09/30
 */
public class App {

    public static void main(String[] args) {

        // 1.得到用户名
        // String username = "weixin_41287260";

        System.out.print("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        scanner.close();

        // 计时，获取开始时间
        long startTime = System.currentTimeMillis();

        System.out.println("\n" + username + ",即将为您生成CSDN博客目录。\n");
        System.out.println("1.正在获取分类专栏的信息，请稍候……");

        // 2.获取该用户的非空的分类专栏列表
        ArrayList<Category> categoryList = new ArrayList<Category>(20);
        categoryList = SpiderUtil.getCategoryList(username);

        // 处理出现问题的情况
        if (categoryList == null) {
            return;
        }
        if (categoryList.isEmpty()) {
            System.err.println("获取分类专栏失败！");
            return;
        } else {
            System.out.println("----获取分类专栏成功，共有" + categoryList.size() + "个非空的分类专栏。");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2.正在获取分类专栏内的博客信息……");
        }

        // 3.获得每个分类专栏的所有文章信息
        for (Category category : categoryList) {
            // 获取该分类的所有博客列表
            ArrayList<Blog> blogs = SpiderUtil.getCategoryBlogs(category);
            // 判空（对于null,因为这个方法已经进行处理，这里就不处理了），非空添加到列表
            if (blogs != null) {
                category.setBlogs(blogs);
            }else {
                return ;
            }
        }
        System.out.println("----获取分类专栏内的博客信息成功！");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("3.即将生成该用户的CSDN博客的导航分类目录文件……");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 4.将数据写到（符合CSDN的markdown编辑器格式的）文件中
        String pathname = "CSDN博客目录-" + FileUtil.getDateString() + ".md";
        FileUtil.generateCsdnList(pathname, categoryList);

        // 计时，获取结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("感谢您使用该工具，此次用时：" + FileUtil.getSecondString(endTime - startTime) + "s，期待下一次的重逢！");
    }
}
