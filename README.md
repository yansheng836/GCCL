# Generate CSDN Category List(GCCL)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/73584fbffb924a42827f373819f7f419)](https://www.codacy.com/gh/yansheng836/GCCL/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=yansheng836/GCCL&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/yansheng836/GCCL.svg?branch=master)](https://travis-ci.org/yansheng836/GCCL)
[![codecov](https://codecov.io/gh/yansheng836/GCCL/branch/master/graph/badge.svg)](https://codecov.io/gh/yansheng836/GCCL)
[![BCH compliance](https://bettercodehub.com/edge/badge/yansheng836/GCCL?branch=master)](https://bettercodehub.com/)
[![GitHub LICENSE ](https://img.shields.io/github/license/yansheng836/GCCL)](https://github.com/yansheng836/GCCL/blob/master/LICENSE.txt)
[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/yansheng836/GCCL?include_prereleases)](https://github.com/yansheng836/GCCL/releases)

## 说明

为CSDN博客分类生成目录导航列表。虽然CSDN的分类专栏支持二级分类，但是到目前为止(2019-9-30)它的层级关系并不密切，或者说是没有关系，所以这里就没有进行分层，仅仅是针对分类进行索引。

样式参考了这篇博客：<https://blog.csdn.net/SnailMann/article/details/88392514>，分类设置为三级目录（如果需要二级目录，可以自行添加），分类里面的博客设置成无序列表：

```markdown
### <font color ="green">Java基础</font>

- [命令行运行javac，报错: 编码 GBK 的不可映射字符 (0x9C)](https://blog.csdn.net/weixin_41287260/article/details/100177044)
```

## 思路

1. 先爬取CSDN博主主页的（非空的）分类专栏的信息：分类专栏的名称、网址、数量。
2. 到每个分类专栏爬取博客列表信息：博客的标题、网址。
3. 将爬取到的博客信息按照特定的格式输出到文件中。

### 注：

- 页面元素分析可见：[页面元素分析](<https://github.com/yansheng836/GCCL/tree/master/页面元素分析>)。
- 因为我将分类信息和博客信息都存放在`ArrayList`中，所以最后生成的目录是有序的，都是按照分类专栏和每个专栏的博客的顺序进行排列的。
  - 分类专栏的排列顺序：可到[分类专栏管理处](https://mp.csdn.net/classify_and_column/list)调整分类专栏的排列顺序。
  - 分类专栏中的博客顺序：不得不吐槽的是：这个顺序默认是随机的，虽然很多人都想CSDN工作人员反映这个问题，但是不知道为啥就是不改了。修改：进到每个分类专栏，点击右上角的`管理博客`-->`批量操作`-->`排序`-->顺序建议使用`按创建时间`的`降序`。

## 效果

请看生成的文件 [博客目录-最新版.md](./博客目录-最新版.md)，或者参考我的CSDN博客目录：[【目录】博客目录 | 先点这里](<https://blog.csdn.net/weixin_41287260/article/details/101735143>)。

## 测试

对多种情况进行了测试，得出了对应的程序运行时间，详见：[程序测试](<https://github.com/yansheng836/GCCL/blob/master/程序测试>)。

## 使用

### 简单使用

下载最新的发布包：[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/yansheng836/GCCL?include_prereleases)](https://github.com/yansheng836/GCCL/releases)

提供脚本，详见：[运行GCCL工具的脚本](<https://github.com/yansheng836/GCCL/tree/master/运行GCCL工具的脚本>)，可直接运行该工具。

然后输入用户名，稍等片刻，即可在当前目录生成`CSDN博客目录.md`文件，复制到CSDN的markdown编辑器即可，当然你也可以添加一些说明。

[我的博客](https://blog.csdn.net/weixin_41287260):(2019-10-14)有97篇原创，含转发共153篇博客，26个非空分类专栏，分类专栏下博客共251篇。相当于原创的当于3倍（因为每篇博客可以选三个分类专栏）。用时在20s~50s之间（与网络状况有关）。

<details>
<summary>
示范如下(点击左边三角形展开)：
</summary>

```shell
-------------------------------------------------------------------------------
-----------------------欢迎使用CSDN博客目录生成工具(GCCL)----------------------
                          _____   _____  _____  _
                         / ____| / ____|/ ____|| |
                        | |  __ | |    | |     | |
                        | | |_ || |    | |     | |
                        | |__| || |____| |____ | |____
                         \_____| \_____|\_____||______|
.
该工具可为您生成“CSDN博客导航目录”,使用前请阅读README,然后按照提示进行操作。
如果在使用过程中遇到问题，可到 https://github.com/yansheng836/GCCL/issues 提问。
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
！！！！！！！！！！！！！！！！！声明：！！！！！！！！！！！！！！！！！！！
该工具仅供学习交流使用，请勿用于商业用途；该工具是完全免费的，不进行任何保证，
版权归 sheng.yan836@gmail.com 所有。
-------------------------------------------------------------------------------
.
__          __  _                          _ 
\ \        / / | |                        | |
 \ \  /\  / /__| | ___ ___  _ __ ___   ___| |
  \ \/  \/ / _ \ |/ __/ _ \| '_ ` _ \ / _ \ |
   \  /\  /  __/ | (_| (_) | | | | | |  __/_|
    \/  \/ \___|_|\___\___/|_| |_| |_|\___(_)
                                             
请输入用户名：weixin_41287260


weixin_41287260,即将为您生成CSDN博客目录。

1.正在获取分类专栏的信息，请稍候……
----所有分类专栏一共有:251篇博客(包含重复的)。
----获取分类专栏信息成功，共有26个非空的分类专栏。
2.正在获取每个分类专栏内的博客信息……
----获取分类专栏内的博客信息成功！
3.正在生成该用户的‘博客导航分类目录’文件……
----生成‘博客导航分类目录’文件成功！！
文件路径为：CSDN博客目录-2019-10-14.21-54-19.md

**感谢您使用该工具,此次用时:41.143秒,期待下一次的重逢!**
  _____                 _ _                _ 
 / ____|               | | |              | |
| |  __  ___   ___   __| | |__  _   _  ___| |
| | |_ |/ _ \ / _ \ / _` | '_ \| | | |/ _ \ |
| |__| | (_) | (_) | (_| | |_) | |_| |  __/_|
 \_____|\___/ \___/ \__,_|_.__/ \__, |\___(_)
                                 __/ |       
                                |___/        
```

效果图：![效果图](https://s2.ax1x.com/2019/10/15/K9cLex.jpg)

</details>

### 开发

1. git clone <https://github.com/yansheng836/GCCL.git>
2. 以Maven形式导入IDE。
3. 运行`/GCCL/src/main/java/xyz/yansheng/main`下面的`App`主程序。
4. 或者按需开发自己的程序。

## Limitation

- 目前只能爬取没有自定义博客域名的博客，博客主页网址形式为：https: //blog.csdn.net/ + username，如我的CSDN用户名为`weixin_41287260`，我的博客主页网址为：<https://blog.csdn.net/weixin_41287260>。
- 如果选择的博客主题在侧边栏没有博客分类专栏，那么该工具无效（如这位博主的：<https://blog.csdn.net/lingshengxueyuan>）。
- 如果某个分类专栏没有设置在前台显示，则不能爬取该分类专栏的信息。（注：如果没有到 [分类专栏管理处](<https://mp.csdn.net/classify_and_column/list>) 进行设置，新建后默认为显示/可见。）
- 前面已经提到了分类专栏可能有分级的情况（一级和二级），但是因为关系不紧密，该工具没有做这方面的处理。进行过测试，发现应该是可以爬取二级分类的。

## CHANGELOG

详见：[CHANGELOG.md](https://github.com/yansheng836/GCCL/blob/master/CHANGELOG.md)。

## License

This work is licensed under a [MIT](https://github.com/yansheng836/GCCL/blob/master/LICENSE.txt).

## Declaration

- 本项目仅用于学习交流使用，**禁止**进行商业目的的开发、发布、运营等。数据所有权归 <http://www.csdn.net/> 所有。

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=yansheng836/GCCL&type=Timeline)](https://star-history.com/#yansheng836/GCCL&Timeline)

