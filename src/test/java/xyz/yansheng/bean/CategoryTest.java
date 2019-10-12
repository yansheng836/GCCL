package xyz.yansheng.bean;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author yansheng
 * @date 2019/09/30
 */
public class CategoryTest {

    /**
     * Test method for {@link xyz.yansheng.bean.Category#Category(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testCategory() {
        String url = "https://blog.csdn.net/weixin_41287260/article/category/8128217";
        String title = "Java基础";
        ArrayList<Blog> blogs = new ArrayList<Blog>();
        
        String url1 = "https://blog.csdn.net/weixin_41287260/article/details/92185040";
        String title1 = "阿里巴巴主导的“华山版《Java 开发手册》”简介";
        Blog blog = new Blog(url1, title1);
        blogs.add(blog);
        blogs.add(blog);
        
        Category category = new Category(url, title, blogs);
        assertNotNull(category);
        System.out.println(category.toString());
    }

}
