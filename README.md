# Generate CSDN Category List

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4462adf5e25f4e40a5adf2350e9c5dbe)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=yansheng836/GCCL&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/yansheng836/GCCL.svg?branch=master)](https://travis-ci.org/yansheng836/GCCL)  [![codecov](https://codecov.io/gh/yansheng836/GCCL/branch/master/graph/badge.svg)](https://codecov.io/gh/yansheng836/GCCL)   [![BCH compliance](https://bettercodehub.com/edge/badge/yansheng836/GCCL?branch=master)](https://bettercodehub.com/)  [![GitHub LICENSE ](https://img.shields.io/github/license/yansheng836/GCCL)](https://github.com/yansheng836/GCCL/blob/master/LICENSE.txt)  [![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/yansheng836/GCCL?include_prereleases)](https://github.com/yansheng836/GCCL/releases)

## 说明

为CSDN博客分类生成目录导航列表。虽然CSDN的分类专栏支持二级分类，但是到目前为止(2019-9-30)它的层级关系并不密切，或者说是没有关系，所以这里就没有进行分层，仅仅是针对分类进行索引。

样式参考了这篇博客：<https://blog.csdn.net/SnailMann/article/details/88392514>，分类设置为三级目录（如果需要二级目录，可以自行添加），分类里面的博客设置成无序列表：

```markdown
### <font color ="green">Java基础</font>

- [命令行运行javac，报错: 编码 GBK 的不可映射字符 (0x9C)](https://blog.csdn.net/weixin_41287260/article/details/100177044)
```

## 效果

请看生成的文件 [CSDN博客目录.md](https://github.com/yansheng836/GCCL/blob/master/CSDN博客目录.md)，或者参考我的CSDN博客目录：[【目录】博客目录 | 先点这里](<https://blog.csdn.net/weixin_41287260/article/details/101735143>)。

## 使用

### 简单使用

下载最新的发布包：[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/yansheng836/GCCL?include_prereleases)](https://github.com/yansheng836/GCCL/releases)

用cmd运行命令：(“*"表示对应版本)

```bash
java -jar GCCL-*-SNAPSHOT.jar
```

然后输入用户名，稍等片刻，即可在当前目录生成`CSDN博客目录.md`文件，复制到CSDN的markdown编辑器即可，当然你也可以添加一些说明。

### 开发

1. git clone <https://github.com/yansheng836/GCCL.git>
2. 以Maven形式导入IDE。
3. 运行`/GCCL/src/main/java/xyz/yansheng/main`下面的`App`主程序。
4. 或者按需开发自己的程序。

## License

This work is licensed under a [MIT](https://github.com/yansheng836/GCCL/blob/master/LICENSE.txt).

## 声明

- 本项目仅用于学习交流使用，**禁止**进行商业目的的开发、发布、运营等。数据所有权归 <http://www.csdn.net/> 所有。