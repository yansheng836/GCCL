package xyz.yansheng.main;

import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;

import org.junit.Test;

/**
 * @author yansheng
 * @date 2019/10/13
 */
public class AppTest {

    /**
     * Test method for {@link xyz.yansheng.main.App#main(java.lang.String[])}.
     */
    @Test
    public void testMain() {
        
        // 第1个用户名正确，23都错误
        String username1 = "weixin_41287260";
        String username2 = "weixin_412872";
        String username3 = "weixin_";

        String[] usernames = {username1, username2, username3};
        for (String username : usernames) {
            // System.setIn(InputStream in) :重新分配“标准”输入流。
            System.setIn(new ByteArrayInputStream(username.getBytes()));
            try {
                App.main(null);
            } catch (Exception e) {
                assertFalse(e instanceof NullPointerException);
            }

        }

    }
    
    @Test
    public void testMain1() {
        
        // 第1个用户名正确，23都错误
        String username1 = "weixin_41287260";
        String username2 = "weixin_412872";
        String username3 = "weixin_";

        String[] usernames = {username1, username2, username3};
//        for (String username : usernames) {
//            // System.setIn(InputStream in) :重新分配“标准”输入流。
//            System.setIn(new ByteArrayInputStream(username.getBytes()));
////            try {
////                App.main(null);
////            } catch (Exception e) {
////                assertFalse(e instanceof NullPointerException);
////            }
//            App.main(null);
//            System.setIn(System.in);
//
//        }
        System.setIn(new ByteArrayInputStream("sdf".getBytes()));
        App.main(null);

    }
}
