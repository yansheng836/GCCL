package xyz.yansheng.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import xyz.yansheng.bean.Blog;
import xyz.yansheng.bean.Category;
import xyz.yansheng.util.FileUtil;
import xyz.yansheng.util.SpiderUtil;

/**
 * 生成CSDN博客分类导航目录。思路：从博客主页获取非空的分类专栏列表，然后到具体的分类专栏页面，获取该分类下的所有博客列表。
 * 最后利用所有的分类信息生成CSDN的markdown编辑器的markdown格式的文件。
 * 
 * @author yansheng
 * @date 2019/09/30
 */
public class App {

    public static void main(String[] args) {
        // 打招呼
        System.out.println(FileUtil.sayWelcome());

        // 1.得到用户名
//        String username = "weixin_41287260";

         System.out.print("请输入用户名：");
         Scanner scanner = new Scanner(System.in);
         String username = scanner.nextLine();
         scanner.close();

        // 计时，获取开始时间
        long startTime = System.currentTimeMillis();

        // 设置计时器，如果5分钟后还不能生成文件，自动停止程序
        long fiveMinute = 5 * 60 * 1000L;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("程序运行时间超过5分钟，已自动停止，请检查网络是否有问题。");
                System.exit(-1);
            }
        }, fiveMinute);

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
            System.out.println("----获取分类专栏信息成功，共有" + categoryList.size() + "个非空的分类专栏。");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2.正在获取每个分类专栏内的博客信息……");
        }

        // 3.获得每个分类专栏的所有文章信息
        for (Category category : categoryList) {
            // 获取该分类的所有博客列表
            ArrayList<Blog> blogs = SpiderUtil.getCategoryBlogs(category);
            // 判空（对于null,直接返回、跳出程序），非空添加到列表
            if (blogs != null) {
                category.setBlogs(blogs);
            } else {
                continue;
            }
        }
        System.out.println("----获取分类专栏内的博客信息成功！");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("3.正在生成该用户的'博客导航分类目录'文件……");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 4.将数据写到（符合CSDN的markdown编辑器格式的）文件中
        String pathname = "CSDN博客目录-" + FileUtil.getDateString() + ".md";
        boolean result = FileUtil.generateCsdnList(pathname, categoryList);

        if (result) {
            System.out.println("----生成'博客导航分类目录'文件成功！！\n文件路径为：" + pathname);
        } else {
            System.out.println("----生成'博客导航分类目录'文件失败！！");
            return;
        }

        // 计时，获取结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("\n**感谢您使用该工具，此次用时：" + FileUtil.getSecondString(endTime - startTime)
            + "，期待下一次的重逢！**");
        // 打招呼
        System.out.println(FileUtil.sayGoodbye());
    }
}
