package xyz.yansheng.bean;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author yansheng
 * @date 2019/09/30
 */
public class CategoryTest {

    private String categoryUrl = "https://blog.csdn.net/weixin_41287260/article/category/8128217";
    private String categoryTitle = "Java基础";
    private Integer count = 29;

    private ArrayList<Blog> blogs = new ArrayList<Blog>(2);
    private String blogUrl = "https://blog.csdn.net/weixin_41287260/article/details/92185040";
    private String blogTitle = "阿里巴巴主导的“华山版《Java 开发手册》”简介";

    /**
     * Test method for {@link xyz.yansheng.bean.Category#Category()}.
     */
    @Test
    public void testCategory0() {

        Blog blog = new Blog(blogUrl, blogTitle);
        blogs.add(blog);
        blogs.add(blog);

        Category category = new Category();
        category.setUrl(categoryUrl);
        category.setTitle(categoryTitle);
        category.setCount(count);
        category.setBlogs(blogs);

        assertNotNull(category);
        System.out.println(category.toString());
    }

    /**
     * Test method for
     * {@link xyz.yansheng.bean.Category#Category(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testCategory() {

        Blog blog = new Blog(blogUrl, blogTitle);
        blogs.add(blog);
        blogs.add(blog);

        Category category = new Category(categoryUrl, categoryTitle, count, blogs);
        assertNotNull(category);
        System.out.println(category.toString());

        // Test method for {@link xyz.yansheng.bean.Category#toStringBlogs()}.
        System.out.println(category.toStringBlogs());

        // Test method for {@link xyz.yansheng.bean.Category#toStringMd()}.
        System.out.println(category.toStringMd());
    }

}
