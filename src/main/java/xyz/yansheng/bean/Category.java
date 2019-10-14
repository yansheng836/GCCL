package xyz.yansheng.bean;

import java.util.ArrayList;

/**
 * 分类专栏实体类，当做是一种特殊的博客类型（继承Blog）；地址为该分类专栏主页地址，多了一个属性为该分类专栏的所有博客列表。
 * 
 * @author yansheng
 * @date 2019/09/30
 */
public class Category extends Blog {

    /**
     * 该分类专栏的所有博客数量
     */
    private Integer count;

    /**
     * 该分类专栏的所有博客列表
     */
    private ArrayList<Blog> blogs;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }

    /**
     * No arg construction method.
     */
    public Category() {}

    /**
     * All args construction method.
     * 
     * @param url
     *            该分类专栏主页地址
     * @param title
     *            名称
     * @param blogs
     *            该分类专栏的所有博客列表
     */
    public Category(String url, String title, Integer count, ArrayList<Blog> blogs) {
        super(url, title);
        this.count = count;
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Category [url=" + getUrl() + ", title=" + getTitle() + ", count=" + getCount()
            + ", blogs=" + blogs + "]";
    }

    // ### <font color ="green">Spring框架</font>
    //
    // - [文章1 title](url)
    // - [文章2 title](url)

    /**
     * 打印该分类的博客列表信息的markdown字符串
     * 
     * @return 该分类的博客列表信息的markdown字符串
     */
    public String toStringBlogs() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Blog blog : blogs) {
            stringBuffer.append("- [");
            stringBuffer.append(blog.getTitle());
            stringBuffer.append("](");
            stringBuffer.append(blog.getUrl());
            stringBuffer.append(")\n");
        }

        return new String(stringBuffer);
    }

    /**
     * 打印该分类的markdown字符串
     * 
     * @return 该分类的markdown字符串
     */
    public String toStringMd() {
        return "### <font color =\"green\">" + getTitle() + "</font>\n\n" + toStringBlogs();
    }

}
