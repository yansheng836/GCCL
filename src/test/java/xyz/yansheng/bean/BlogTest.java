package xyz.yansheng.bean;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author yansheng
 * @date 2019/09/30
 */
public class BlogTest {

    /**
     * Test method for {@link xyz.yansheng.bean.Blog#Blog(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testBlog() {
        String url = "https://blog.csdn.net/weixin_41287260/article/details/92185040";
        String title = "阿里巴巴主导的“华山版《Java 开发手册》”简介";
        Blog blog = new Blog(url, title);
        assertNotNull(blog);
        System.out.println(blog.toString());
    }

}
