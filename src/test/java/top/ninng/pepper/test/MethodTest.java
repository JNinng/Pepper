package top.ninng.pepper.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ninng.pepper.OkHttpPepperClient;
import top.ninng.pepper.Pepper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 14:33
 * @Version 1.0
 */
public class MethodTest {

    private static final String TAG = MethodTest.class.getSimpleName();
    private static final Logger log = LoggerFactory.getLogger(MethodTest.class);

    // 需要配置正确的服务器及账号相关信息
    private final String URL = "http://ninng.top/seafdav/test/sardinetest";
    private final String USER_NAME = "xxx1599xx@163.com";
    private final String PASSWORD = "test";

    private final String TEST_DIR_NAME = "/test";
    private final String TEST_FILE_NAME = "/test.txt";

    private final Pepper pepper = new OkHttpPepperClient();

    @Test
    public void copy() throws Exception {
        pepper.copy(URL + TEST_FILE_NAME, URL + TEST_DIR_NAME + TEST_FILE_NAME);
    }

    @Test
    public void createDirectory() throws Exception {
        String dir = URL + TEST_DIR_NAME;
        if (!exists(dir)) {
            pepper.createDirectory(dir);
        }
        exists(dir);
    }

    @Test
    public void delete() throws Exception {
        String del = URL + TEST_FILE_NAME;
        boolean exists = exists(del);
        if (exists) {
            pepper.delete(del);
        }
        log.info(del + (!pepper.exists(del) ? ": deleted" : ": delete error"));
    }

    @Test
    public void exists() throws Exception {
        exists(URL + TEST_FILE_NAME);
    }

    public boolean exists(String url) throws Exception {
        boolean exists = pepper.exists(url);
        log.info(url + (exists ? ": exists" : ": no exists"));
        return exists;
    }

    public void get(String sourcesUrl) throws Exception {
        InputStream in = pepper.get(sourcesUrl);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
        log.info(sourcesUrl + ": " + byteArrayOutputStream.toString());
    }

    @Test
    public void get() throws Exception {
        get(URL + TEST_FILE_NAME);
    }

    @Test
    public void list() throws Exception {
        log.info(pepper.list(URL).toString());
    }

    @Test
    public void move() throws Exception {
        String mv = URL + TEST_FILE_NAME;
        pepper.move(mv, URL + TEST_DIR_NAME + TEST_FILE_NAME);
    }

    @Test
    public void put() throws Exception {
        String txt = URL + TEST_FILE_NAME;
        pepper.put(txt, "test content".getBytes());
        get(txt);
    }

    @Test
    public void search() throws Exception {
        log.info(pepper.search(URL, "", "test").toString());
    }

    @Before
    public void setUp() {
        pepper.setCredentials(USER_NAME, PASSWORD);
    }
}
