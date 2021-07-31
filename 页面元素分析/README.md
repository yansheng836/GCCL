# 页面元素分析

## 2021年7月31日更新

后面页面HTML元素有更新，请以最新的内容为主，详见：

- [1.0博客主页的分类专栏的分析.html](./1.0博客主页的分类专栏的分析.html)
- [2.每个分类专栏的分析.html](./2.每个分类专栏的分析.html)

## 1.博客主页的分类专栏的分析

### 是否有文章的区别：

非空有这个元素` <span class="count float-right">27篇</span> `，空的没有。

如：博客网址：<https://blog.csdn.net/weixin_41287260>。

```html
<div id="asideCategory" class="aside-box flexible-box">
     <h3 class="aside-title">分类专栏</h3> 
    <div class="aside-content">
        <ul>
            <li class="">
                <a class="clearfix" href="https://blog.csdn.net/weixin_41287260/article/category/8128217">
                    <img src="https://img-blog.csdnimg.cn/20190924152723372.jpg?x-oss-process=image/resize,m_fixed,h_64,w_64" alt="" onerror="this.src='https://img-blog.csdnimg.cn/20190918135101160.png'"> <span class="title oneline">Java基础</span>  <span class="count float-right">27篇</span> 
                </a>
            </li>
……
            <li class="">
                <a class="clearfix" href="https://blog.csdn.net/weixin_41287260/article/category/9335742">
                    <img src="https://img-blog.csdnimg.cn/20190927003730623.jpeg?x-oss-process=image/resize,m_fixed,h_64,w_64" alt="" onerror="this.src='https://img-blog.csdnimg.cn/20190918140012416.png'"> <span class="title oneline">SpringMVC</span> 
                </a>
            </li>
……
            <li class="">
                <a class="clearfix" href="https://blog.csdn.net/weixin_41287260/article/category/8647797">
                    <img src="https://img-blog.csdnimg.cn/20190908130720970.jpg?x-oss-process=image/resize,m_fixed,h_64,w_64" alt="" onerror="this.src='https://img-blog.csdnimg.cn/20190927151117521.png'"> <span class="title oneline">CMD</span>  <span class="count float-right">9篇</span> 
                </a>
            </li>
        </ul>
    </div>
    ……
 </div>
```

### 是否有二级分类的区别：

一级和二级的区别：li标签：一级：`<li class="">`，二级：`<li class="indentation">`。

如：博客网址：<https://blog.csdn.net/qing_gee>（一共7个分类专栏，有二级分类专栏。）。

```html
<ul>
    <li class="">
        <a class="clearfix" href="https://blog.csdn.net/qing_gee/article/category/9264687">
            <img src="https://img-blog.csdnimg.cn/20190909075237510.png?x-oss-process=image/resize,m_fixed,h_64,w_64" alt="" onerror="this.src='https://img-blog.csdnimg.cn/20190918140158853.png'"> <span class="title oneline">Java</span>
 <span class="count float-right">52篇</span>
        </a>
    </li>
    <li class="indentation">
        <a class="clearfix" href="https://blog.csdn.net/qing_gee/article/category/9339106">
            <img src="https://img-blog.csdnimg.cn/20190910102621680.jpeg?x-oss-process=image/resize,m_fixed,h_64,w_64" alt="" onerror="this.src='https://img-blog.csdnimg.cn/20190927151132530.png'"> <span class="title oneline">Java基础</span>
 <span class="count float-right">20篇</span>
        </a>
    </li>
……
</ul>
```

## 2.每个分类专栏的分析

### 有多页时，URL规律

当该分类专栏的数量超过20时，会分页，而URL是有规律，如：”Java基础“这个分类专栏，第一页地址为：<https://blog.csdn.net/weixin_41287260/article/category/8128217>，第二页地址为：<https://blog.csdn.net/weixin_41287260/article/category/8128217/2>，就是后面加上了页数，其实第一页可以是：<https://blog.csdn.net/weixin_41287260/article/category/8128217/1>。

### 每个博客的信息

这是[该页](<https://blog.csdn.net/weixin_41287260/article/category/8128217/1>)的博客列表元素：

```html
<ul class="column_article_list">
    <li>
        <a href="https://blog.csdn.net/weixin_41287260/article/details/100177044" target="_blank" rel="noopener">
            <div class="column_article_title"> <span class="article-type type-1 float-left">原创</span> 
                 <h2 class="title">命令行运行javac，报错: 编码 GBK 的不可映射字符 (0x9C)</h2> 
            </div>
            <div class="column_article_desc">针对“命令行运行javac，报错: 编码 GBK 的不可映射字符 (0x9C)”问题，这篇文章分3部分内容进行了讲解：错误情况示例；原因分析；解决办法。</div>
            <div class="column_article_data"> <span>2019-08-31 20:59:14</span>  <span>阅读数 60</span>  <span>评论数 0</span>
            </div>
        </a>
    </li>
……
    <li>
        <a href="https://blog.csdn.net/weixin_41287260/article/details/100177023" target="_blank" rel="noopener">
            <div class="column_article_title"> <span class="article-type type-2 float-left">转载</span> 
                 <h2 class="title">错误: 找不到或无法加载主类 Demo.class 的解决方法</h2> 
            </div>
            <div class="column_article_desc">问题：在命令行运行class文件，发生错误: 找不到或无法加载主类 Demo.class 原因：因为java 命令后面的文件不能有后缀名。 解决办法：运行java时候，后面的文件去掉后缀名。</div>
            <div class="column_article_data"> <span>2019-08-31 20:47:26</span>  <span>阅读数 115</span>  <span>评论数 0</span> 
            </div>
        </a>
    </li>
    ………………
</ul>
```
