package xyz.yansheng.util;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xyz.yansheng.bean.Blog;
import xyz.yansheng.bean.Category;

/**
 * 工具类
 * 
 * @author yansheng
 * @date 2019/09/30
 */
public class Utility {

    /**
     * 获取该用户的非空的分类列表
     * 
     * @param username
     *            用户名
     * @return 分类专栏列表
     */
    public static ArrayList<Category> getCategoryList(String username) {

        // 用户博客主页地址
        String url = "https://blog.csdn.net/" + username;

        ArrayList<Category> categoryList = new ArrayList<Category>(20);

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element asideElement = doc.selectFirst("aside");
        Element ulElement = asideElement.selectFirst("ul");
        Elements liElements = ulElement.select("li a");
        for (Element liElement : liElements) {
            // 标题
            Element spanElement1 = liElement.selectFirst("span.title.oneline");

            // 博客数量，如果这个不为空，表示该分类有文章，添加该分类到列表
            Element spanElement2 = liElement.selectFirst("span.count.float-right");
            if (spanElement2 != null) {
                String href = liElement.attr("href");
                String title = spanElement1.text();

                Category category = new Category();
                category.setUrl(href);
                category.setTitle(title);
                categoryList.add(category);
            }
        }

        return categoryList;
    }

    /**
     * 获取该分类的所有博客列表
     * 
     * @param category
     *            分类实体类（blogs为空）
     * @return 分类实体类（blogs不为空）
     */
    public static Category getCategoryBlogs(Category category) {
        String url = category.getUrl();
        ArrayList<Blog> blogs = new ArrayList<Blog>(20);

        // 获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element ulElement = doc.selectFirst("ul.column_article_list");
        Elements liElements = ulElement.select("li a");
        for (Element liElement : liElements) {
            // 获取每个博客的地址和标题
            String href = liElement.attr("href");
            Element titleElement = liElement.selectFirst("h2.title");
            String title = titleElement.text();

            Blog blog = new Blog(href, title);
            blogs.add(blog);
        }

        category.setBlogs(blogs);
        return category;
    }

}
