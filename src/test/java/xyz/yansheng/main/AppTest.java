package xyz.yansheng.main;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import org.junit.Test;

/**
 * @author yansheng
 * @date 2019/10/13
 */
public class AppTest {

    /**
     * 测试多组数据（包含多种情况）
     * 
     * Test method for {@link xyz.yansheng.main.App#main(java.lang.String[])}.
     */
    @Test
    public void testMain() {

        // 0X（即测试编号0开头）：CSDN排名前几的博主。
        String username01 = "stpeace";
        String username02 = "phphot";

        // 1X：随机选的博客进行测试。
        String username10 = "weixin_41287260";
        String username11 = "yilovexing";
        String username12 = "hjx9452";
        String username13 = "zhouzhiwengang";
        String username14 = "blogdevteam";

        // 2X：可能会有问题的测试：博客主页没有显示分类专栏。
        String username20 = "weixin_30892987";
        String username21 = "lingshengxueyuan";
        String username22 = "love_caicai";

        // 3X：可能会有问题的测试：分类专栏有层级（即有二级分类）。
        String username30 = "qing_gee";

        // 4X：错误的用户名。
        String username40 = "weixin_41287";

        // 5X：可能会有问题的测试：自定义域名，用户名形式为：https: //username.blog.csdn.net/
        // ，如：<https://wuyeliang.blog.csdn.net/>。
        String username50 = "wuyeliang";
        String username51 = "coderfix";

        String[] usernames = {username10, username11, username12, username13, username14,
            username20, username21, username22, username30, username40, username50, username51};

        for (String username : usernames) {
            // System.setIn(InputStream in) :重新分配“标准”输入流。
            System.setIn(new ByteArrayInputStream(username.getBytes()));
            try {
                App.main(null);
            } catch (NullPointerException e) {
                fail("程序运行错误，出现了空指针异常！！！");
            }
        }

    }

}
