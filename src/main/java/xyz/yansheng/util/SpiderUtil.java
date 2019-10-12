package xyz.yansheng.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Connection;
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
public class SpiderUtil {

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
//            doc = Jsoup.connect(url).get();
            String charsetName = "UTF-8";
            doc = Jsoup.parse(new URL(url).openStream(),charsetName,url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element asideElement = doc.selectFirst("aside");
        Element asideCategoryElement = doc.selectFirst("div#asideCategory");
//        System.out.println(asideCategoryElement);
        Element ulElement = asideCategoryElement.selectFirst("ul");
        Elements liElements = ulElement.select("li a");
//        System.out.println(liElements);
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

        ArrayList<Blog> blogs = new ArrayList<Blog>(20);
        String url = category.getUrl();

        // 获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 先判断该分类是否有多页
        // 标签信息，详见：某个分类专栏的文章列表的ul标签 - 多页.txt
        Element pageBoxElement = doc.selectFirst("div.pagination-box");

        // 非空表示该分类博客数量多，有多页
        if (pageBoxElement != null) {
            // 循环每页，直到遇到该页的博客列表空就停止
            for (int i = 1;; i++) {
                String pageUrl = url + "/" + i;
                // 获取该页博客列表，如果没有博客，返回null
                ArrayList<Blog> blogs1 = getPageBlogs(pageUrl);

                // 如果不为空，添加到列表；为空时，直接跳出循环。
                if (blogs1 != null) {
//                    System.out.println(pageUrl);
                    blogs.addAll(blogs1);
                    // 如果该页博客数量少于20，说明没有下一页了。
                    if (blogs1.size() < 20) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else {
            // 单页
            ArrayList<Blog> blogs1 = getPageBlogs(url);
            blogs.addAll(blogs1);
        }

        category.setBlogs(blogs);
        return category;
    }

    /**
     * 查找分类的每页的博客列表信息
     * 
     * @param pageUrl
     * @return
     */
    public static ArrayList<Blog> getPageBlogs(String pageUrl) {

        ArrayList<Blog> blogs = new ArrayList<Blog>(20);

        // 获取文档对象
        Document doc = null;
        try {
            Connection con = Jsoup.connect(pageUrl).userAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .timeout(30000); // 设置连接超时时间

            Connection.Response response = con.execute();

            if (response.statusCode() == 200) {
                doc = con.get();
            } else {
//                System.out.println(response.statusCode());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element ulElement = doc.selectFirst("ul.column_article_list");
        Elements liElements = ulElement.select("li a");
        // 如果这个list为空，则说明该页面为空，没有博客。
        if (liElements.isEmpty()) {
            return null;
        }

        for (Element liElement : liElements) {
            // 获取每个博客的地址和标题
            String href = liElement.attr("href");
            Element titleElement = liElement.selectFirst("h2.title");
            String title = titleElement.text();

            Blog blog = new Blog(href, title);
//            System.out.println(blog);
            blogs.add(blog);
        }

        return blogs;
    }

}
