 package xyz.yansheng.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import xyz.yansheng.bean.Category;

/**
 * @author yansheng
 * @date 2019/09/30
 */
public class UtilityTest {

    /**
     * Test method for {@link xyz.yansheng.util.Utility#getCategoryList(java.lang.String)}.
     */
    @Test
    public void testGetCategoryList() {
//        fail("Not yet implemented");
    }

    /**
     * Test method for {@link xyz.yansheng.util.Utility#getCategoryBlogs(xyz.yansheng.bean.Category)}.
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
        
        Category category1 = Utility.getCategoryBlogs(category);
        System.out.println(category1.toString());
    }

    /**
     * Test method for {@link xyz.yansheng.util.Utility#getPageBlogs(java.lang.String)}.
     */
    @Test
    public void testGetPageBlogs() {
//        fail("Not yet implemented");
    }

}
