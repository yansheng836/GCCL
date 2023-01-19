package xyz.yansheng.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import xyz.yansheng.bean.Blog;
import xyz.yansheng.bean.Category;

/**
 * @author yansheng
 * @date 2019/10/12
 */
public class FileUtilTest {

    /**
     * Test method for
     * {@link xyz.yansheng.util.FileUtil#generateCsdnList(java.lang.String, java.util.ArrayList)}.
     */
    @Test
    public void testGenerateCsdnList() {

        String username = "weixin_41287260";
        ArrayList<Category> categoryList = new ArrayList<Category>(2);
        // 这里只测试一个分类专栏
        categoryList.add(SpiderUtil.getCategoryList(username).get(0));

        for (Category category : categoryList) {
            ArrayList<Blog> blogs = new ArrayList<Blog>(20);
            blogs = SpiderUtil.getCategoryBlogs(category);
            category.setBlogs(blogs);
        }

        String pathname = "./test/CSDN博客目录-" + FileUtil.getDateString() + ".md";
        boolean result = FileUtil.generateCsdnList(pathname, categoryList);

        if (result) {
            System.out.println("----生成博客分类导航目录成功！！\n文件路径为：" + pathname);
        } else {
            System.out.println("----生成博客分类导航目录失败！！");
            return;
        }
    }

    /**
     * Test method for {@link xyz.yansheng.util.FileUtil#getDateString()}.
     */
    @Test
    public void testGetDateString() {
        String dateString = FileUtil.getDateString();
        assertNotNull(dateString);
        System.out.println(dateString);
    }

    /**
     * Test method for {@link xyz.yansheng.util.FileUtil#getSecondString(long)}.
     */
    @Test
    public void testGetSecondString() {
        // 1.测试时间不超过60秒的情况 
        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        System.out.println("程序运行时间： " + FileUtil.getSecondString(endTime - startTime));

        // 2.测试时间超过60秒的情况

        Long longTime1 = 1L;
        Long longTime2 = 12L;
        Long longTime3 = 123L;
        Long longTime4 = 1234L;
        Long longTime5 = 12345L;
        Long longTime6 = 60000L;
        Long longTime7 = 61234L;
        Long longTime8 = 123456L;
        Long longTime9 = 4 * 60 * 1000L;
        Long longTime10 = 40 * 60 * 1000L;
        Long[] longs = {longTime1, longTime2, longTime3, longTime4, longTime5, longTime6, longTime7,
                longTime8, longTime9, longTime10};
        for (Long longTime : longs) {
            System.out.println("转换时间： " + longTime + "ms -->" + FileUtil.getSecondString(longTime));
        }

    }

    /**
     * Test method for {@link xyz.yansheng.util.FileUtil#sayGoodbye()}.
     */
    @Test
    public void testSayGoodbye() {

        String goodbye = FileUtil.sayGoodbye();
        assertNotNull(goodbye);
        System.out.println("hello");
        System.out.println(goodbye);
        System.out.println("hello");

    }

    /**
     * Test method for {@link xyz.yansheng.util.FileUtil#sayWelcome()}.
     */
    @Test
    public void testSayWelcome() {

        String welcome = FileUtil.sayWelcome();
        assertNotNull(welcome);
        System.out.println("hello");
        System.out.println(welcome);
        System.out.println("hello");
    }

    /**
     *
     */
    @Test
    public void testGetFileName() {

//        System.out.println(FileUtil.getFileName("1212\\"));
//        System.out.println(FileUtil.getFileName("1212/"));
//        System.out.println(FileUtil.getFileName("1212?"));
//        System.out.println(FileUtil.getFileName("1212*"));
        assertEquals("1212-", FileUtil.getFileName("1212\\"));
        assertEquals("1212-", FileUtil.getFileName("1212/"));
        assertEquals("1212-", FileUtil.getFileName("1212?"));
        assertEquals("1212-", FileUtil.getFileName("1212*"));
        assertEquals("1212\\", FileUtil.getFileName("1212\\"));

    }
}
