package xyz.yansheng.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
     * Test method for {@link xyz.yansheng.util.FileUtil#generateCsdnList(java.lang.String, java.util.ArrayList)}.
     */
    @Test
    public void testGenerateCsdnList() {

        String username = "weixin_41287260";
        System.out.println("\n" + username + ",感谢您使用该工具，即将为你生成CSDN博客目录。\n");

        System.out.println("正在爬取数据，请稍候……\n");

        ArrayList<Category> categoryList = new ArrayList<Category>(2);
        // 这里只测试一个分类专栏
        categoryList.add(SpiderUtil.getCategoryList(username).get(0));

        if (categoryList == null || categoryList.isEmpty()) {
            System.out.println("categoryList == null || categoryList.isEmpty()");
            fail("categoryList == null || categoryList.isEmpty()");
        }
        
        for (Category category : categoryList) {
            ArrayList<Blog> blogs = new ArrayList<Blog>(20);
            blogs = SpiderUtil.getCategoryBlogs(category);
            if (blogs != null && !blogs.isEmpty()) {
                category.setBlogs(blogs);
            } else {
                fail("获取该分类专栏：" + category.getTitle() + " 的数据失败");
            }
        }

        String pathname = "./test/CSDN博客目录-" + FileUtil.getDateString() + ".md";
        FileUtil.generateCsdnList(pathname, categoryList);
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
        long startTime = System.currentTimeMillis(); // 获取开始时间

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        System.out.println("程序运行时间： " + FileUtil.getSecondString(endTime - startTime) + "s");
    }

}
