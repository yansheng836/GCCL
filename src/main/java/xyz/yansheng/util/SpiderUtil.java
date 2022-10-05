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
 * 爬虫工具类
 *
 * @author yansheng
 * @date 2019/09/30
 */
public class SpiderUtil {

    public static final String UTF8 = "UTF-8";
    public static final int PER_PAGE_COUNT = 40;

    /**
     * 获取该用户的非空的分类列表。（注意这里要进行异常判断，如用户名输入错误等。后面不在进行处理。）
     *
     * @param username 用户名
     * @return 分类专栏列表。如果是账号有问题返回null（用object==null判断）,如果是爬取的网页元素有问题返回categoryList（用list.isEmpty()判断）
     */
    public static ArrayList<Category> getCategoryList(String username) {

        // 用户博客主页地址
        String url = "https://blog.csdn.net/" + username;

        ArrayList<Category> categoryList = new ArrayList<Category>(PER_PAGE_COUNT);

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url).openStream(), UTF8, url);
            // System.out.println("doc" + doc);
        } catch (IOException e) {
            System.err.println("访问该用户：" + username + " 主页：" + url + " 失败，请检查用户名是否输入正确！！");
            return null;
            // throw new IllegalArgumentException("该用户主页：" + url + " 访问失败，请检查用户名是否输入正确！！");
        }

        // 为防止关键的页面元素发生变化时，出现问题，这里直接捕获空指针异常
        try {
            Element asideCategoryElement = doc.selectFirst("div.user-special-column.user-profile-aside-common-box");
            Element ulElement = asideCategoryElement.selectFirst("ul");
            // System.out.println("ulElement" + ulElement);
            Elements liElements = ulElement.select("li");
            // 统计所有分类专栏一共有多少博客（包含重复的）
            int num = 0;
            for (Element liElement : liElements) {
                // 标题
                Element titleElement = liElement.selectFirst("a span");
                // 博客数量，如果这个不为空，表示该分类有文章，添加该分类到列表
                Element countElement = liElement.selectFirst("div.special-column-num");
                // System.out.println("countElement:" + countElement);
                if (countElement != null) {
                    // 依次获取分类专栏的网址、标题、博客数量
                    liElement = liElement.selectFirst("a");
                    String href = liElement.attr("href");
                    String title = titleElement.text();
                    String countString = countElement.text();
                    countString = countString.replace("篇", "");
                    Integer count = Integer.parseInt(countString);
                    // System.out.println("count:" + count);
                    num = num + count;

                    // 如果非空就判断获取的数据是否完整，这里选第一个进行测试
                    if ("".equals(href) || "".equals(title) || count == null) {
                        System.err.println("在获取‘分类专栏’的信息时发生错误，可能是获取数据的关键页面元素发生了变化，请进行排查。");
                        continue;
                    }
                    // 如果非空，就创建该分类专栏，并将其添加到列表中
                    Category category = new Category();
                    category.setUrl(href);
                    category.setTitle(title);
                    category.setCount(count);
                    categoryList.add(category);
                }
            }
            System.out.println("----所有分类专栏一共有:" + num + "篇博客(包含重复的)。");
        } catch (NullPointerException e) {
            System.err.println("在获取‘分类专栏’的信息时发生空指针异常，可能是获取数据的关键页面元素发生了变化，请进行排查。");
            return null;
        }

        return categoryList;
    }

    /**
     * 获取该分类的所有博客列表（1页20篇博客，可能有多页）
     *
     * @param category 分类实体类（blogs为空）
     * @return 博客列表（出现问题、异常返回null）
     */
    public static ArrayList<Blog> getCategoryBlogs(Category category) {

        Integer count = category.getCount();
        // 求该分类专栏的博客有多少页，一页有20篇博客，如果有21篇，就说明有2页
        // 2021年7月31日20:50:33更新 没有再按照分页显示，全部一页加载 https://blog.csdn.net/weixin_41287260/category_8128217.html
//        int maxPageCount = 20;
        int blogPage = count / PER_PAGE_COUNT;
        if (count % PER_PAGE_COUNT != 0) {
            blogPage++;
        }
        System.out.println("----专栏:【" + category.getTitle()+"】的文章数量为:" + count);
        // System.out.println("\n\n该分类专栏的博客页数：" + blogPage);

        ArrayList<Blog> blogs = new ArrayList<Blog>(count);
        String url = category.getUrl();
//        System.out.println("url:" + url);
        // 循环爬取每页的博客信息
        for (int i = 1; i <= blogPage; i++) {
            String pageUrl = url.replace(".html", "") + "_" + i + ".html";
//            System.out.println("pageUrl:" + pageUrl);

            ArrayList<Blog> pageBlogs = new ArrayList<Blog>(PER_PAGE_COUNT);
            pageBlogs = getPageBlogs(pageUrl);

            // 如果不为空，添加到列表；为空时，直接跳出循环。
            if (pageBlogs != null) {
                blogs.addAll(pageBlogs);
            }
        }

        return blogs;
    }

    /**
     * 查找分类的每页的博客列表信息（这个方法只爬取单页的数据）
     *
     * @param pageUrl 分类专栏的网址（如果有多页，表示某一页的地址）
     * @return 博客列表（出现问题、异常返回null）
     */
    public static ArrayList<Blog> getPageBlogs(String pageUrl) {

        // 定义：爬取数据出现错误时，返回的错误语句。（因为这种情况比较多，这里进行统一定义）
        String errorString = "爬取：" + pageUrl + " 时出现问题！可能是关键的页面元素发生了变化，请进行排查。";

        ArrayList<Blog> blogs = new ArrayList<Blog>();

        // 获取文档对象
        Document doc = null;
        try {
            Connection con = Jsoup.connect(pageUrl).userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                    .timeout(30000);
            Connection.Response response = con.execute();

            int successCode = 200;
            if (response.statusCode() == successCode) {
                doc = con.get();
            } else {
                System.err.println("爬取：" + pageUrl + " 时出现问题，返回的状态码为：" + response.statusCode());
                return null;
            }
        } catch (IOException e) {
            System.err.println("爬取：" + pageUrl + " 时出现问题！！！");
            System.err.println("生成的博客目录可能会不完整！！！建议重新生成！！！");
            return null;
        }

        // 为防止关键的页面元素发生变化时，出现问题，这里直接捕获空指针异常
        try {
            Element ulElement = doc.selectFirst("ul.column_article_list");
            Elements liElements = ulElement.select("li a");
            // 如果这个list为空，则说明该页面为空，没有博客。
            if (liElements.isEmpty()) {
                System.err.println(errorString);
                return null;
            }

            for (Element liElement : liElements) {
                // 获取每个博客的地址和标题
                String href = liElement.attr("href");
                Element titleElement = liElement.selectFirst("h2.title");
                String title = titleElement.text();
//                title = title.replace("原创","").replace("转载","")
//                    .replace("翻译","").trim();

                // 判断获取的数据是否为空字符串
                if ("".equals(href) || "".equals(title)) {
                    System.err.println(errorString);
                    return null;
                }

                Blog blog = new Blog(href, title);

                blogs.add(blog);
            }

        } catch (NullPointerException e) {
            System.err
                    .println("在获取‘分类专栏’：" + pageUrl + " 的博客的信息时发生空指针异常，可能是获取数据的关键页面元素发生了变化，请进行排查。");
            return null;
        }

        return blogs;
    }

}
