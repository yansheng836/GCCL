# Generate CSDN Category List

## 说明

为CSDN博客分类生成目录导航列表。虽然CSDN的分类专栏支持二级分类，但是到目前为止(2019-9-30)它的层级关系并不密切，或者说是没有关系，所以这里就没有进行分层，仅仅是针对分类进行索引。

样式参考了这篇博客：<https://blog.csdn.net/SnailMann/article/details/88392514>，分类设置为三级目录（如果需要二级目录，可以自行添加），分类里面的博客设置成无序列表：

```markdown
### <font color ="green">Java基础</font>

- [命令行运行javac，报错: 编码 GBK 的不可映射字符 (0x9C)](https://blog.csdn.net/weixin_41287260/article/details/100177044)
```

## 效果

请看生成的文件 [CSDN博客目录.md](https://github.com/yansheng836/GCCL/blob/master/CSDN博客目录.md)

## 使用

1. git clone <https://github.com/yansheng836/GCCL.git>
2. 以Maven形式导入IDE。
3. 运行`/GCCL/src/main/java/xyz/yansheng/main`下面的`App`主程序。
4. 或者按需开发自己的程序。

## License

This work is licensed under a [MIT](https://github.com/yansheng836/GCCL/blob/master/LICENSE.txt).

## 声明

- 本项目仅用于学习交流使用，**禁止**进行商业目的的开发、发布、运营等。数据所有权归 <http://www.csdn.net/> 所有。