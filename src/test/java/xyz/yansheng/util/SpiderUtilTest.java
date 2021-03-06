package xyz.yansheng.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import xyz.yansheng.bean.Blog;
import xyz.yansheng.bean.Category;

/**
 * 爬虫工具类，作用：爬取CSDN博客的数据，如分类专栏的信息、分类专栏下的博客信息。
 * 
 * @author yansheng
 * @date 2019/10/12
 */
public class SpiderUtilTest {

    /**
     * 这里要进行异常处理：判断返回的列表是否为null，后者是空。
     * 
     * Test method for {@link xyz.yansheng.util.SpiderUtil#getCategoryList(java.lang.String)}.
     */
    @Test
    public void testGetCategoryList() {

        // 第3个用户名正确，12都错误
        String username1 = "weixin_";
        String username2 = "weixin_412872";
        String username3 = "weixin_41287260";

        String[] usernames = {username1, username2, username3};

        ArrayList<Category> categoryList = new ArrayList<Category>(20);
        for (String username : usernames) {
            categoryList = SpiderUtil.getCategoryList(username);

            // 1.判断账号是否正确.2.判断列表是否为空。
            if (categoryList == null || categoryList.isEmpty()) {
                System.out.println("categoryList == null || categoryList.isEmpty()");
                continue;
            }
            assertNotNull(categoryList);

            System.out.println("\n----用户： " + username + " 共有" + categoryList.size() + "个非空的分类专栏");
            categoryList.forEach((Category category) -> System.out.println(category.toString()));
        }

    }

    /**
     * Test method for
     * {@link xyz.yansheng.util.SpiderUtil#getCategoryBlogs(xyz.yansheng.bean.Category)}.
     */
    @Test
    public void testGetCategoryBlogs() {

        // 测试准备工作：
        String username = "weixin_41287260";
        ArrayList<Category> categoryList = new ArrayList<Category>(20);
        categoryList = SpiderUtil.getCategoryList(username);
        for (Category category : categoryList) {

            // 主要是为了测试这个方法
            ArrayList<Blog> blogs = new ArrayList<Blog>(category.getCount());
            blogs = SpiderUtil.getCategoryBlogs(category);

            assertNotNull(blogs);

            // 判空（对于null,因为这个方法已经进行处理，这里就不处理了）
            if (blogs != null && !blogs.isEmpty()) {
                category.setBlogs(blogs);

                System.out.println(
                    "\n分类专栏：" + category.getTitle() + " 有" + category.getBlogs().size() + "篇文章");
                blogs.forEach((Blog blog) -> System.out.println(blog.toString()));
            } else {
                fail("获取该分类专栏：" + category.getTitle() + " 的数据失败");
            }
        }

    }

    /**
     * Test method for {@link xyz.yansheng.util.SpiderUtil#getPageBlogs(java.lang.String)}.
     */
    @Test
    public void testGetPageBlogs() {

        // 28篇，多页的；但是该方法只爬取一页的数据（20篇博客）
        String pageUrl1 = "https://blog.csdn.net/weixin_41287260/article/category/8128217";
        // 2篇，单页的
        String pageUrl2 = "https://blog.csdn.net/weixin_41287260/article/category/9305158";
        // 错误的网址（正常情况下，应该不会出现这种情况，因为分类专栏的网址都是从主页进行爬取的）
        String pageUrl3 = "https://blog.csdn.net/weixin_41287260/article/category/93";

        String[] pageUrls = {pageUrl1, pageUrl2, pageUrl3};

        for (String pageUrl : pageUrls) {
            ArrayList<Blog> blogs = new ArrayList<Blog>(20);
            blogs = SpiderUtil.getPageBlogs(pageUrl);

            // 判空（因为SpiderUtil.getPageBlogs(pageUrl)出现问题返回null）
            if (pageUrl3.equals(pageUrl)) {
                assertNull(blogs);
                continue;
            } else {
                assertNotNull(blogs);
            }

            System.out.println("该分类专栏的博客数量为：" + blogs.size());
            blogs.forEach((Blog blog) -> System.out.println(blog));
            System.out.println();
        }
    }

}
