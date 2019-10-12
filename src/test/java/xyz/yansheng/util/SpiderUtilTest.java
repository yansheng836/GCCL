 package xyz.yansheng.util;

import static org.junit.Assert.assertFalse;
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
public class SpiderUtilTest {

    /**
     * Test method for {@link xyz.yansheng.util.SpiderUtil#getCategoryList(java.lang.String)}.
     */
    @Test
    public void testGetCategoryList() {
        String username = "weixin_41287260";
        ArrayList<Category> categoryList = new ArrayList<Category>(20);
        categoryList = SpiderUtil.getCategoryList(username);
        
        assertFalse(categoryList.isEmpty());
        
        if (categoryList.isEmpty()) {
            System.out.println("获取分类专栏失败！");
            return;
        }
        categoryList.forEach((Category category)->System.out.println(category.toString()));
        
    }

    /**
     * Test method for {@link xyz.yansheng.util.SpiderUtil#getCategoryBlogs(xyz.yansheng.bean.Category)}.
     */
    @Test
    public void testGetCategoryBlogs() {
        String url = "https://blog.csdn.net/weixin_41287260/article/category/8128217";
        String url1 = "https://blog.csdn.net/weixin_41287260/article/category/8242807";
        String url2 = "https://blog.csdn.net/weixin_41287260/article/category/8757415";
        String title = "Java基础";
        
        Category category = new Category();
        category.setUrl(url2);
        category.setTitle(title);
        
        assertNotNull(category);
//        System.out.println(category.toString());
        
        Category category1 = SpiderUtil.getCategoryBlogs(category);
//        System.out.println(category1.toString());
        ArrayList<Blog> blogs = category1.getBlogs();
        blogs.forEach((Blog blog)->System.out.println(blog.toString()));
        
    }

    /**
     * Test method for {@link xyz.yansheng.util.SpiderUtil#getPageBlogs(java.lang.String)}.
     */
    @Test
    public void testGetPageBlogs() {
        fail("Not yet implemented");
    }

}
