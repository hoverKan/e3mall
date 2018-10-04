package cn.e3mall.fast;

import cn.e3mall.common.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author hoverkan
 * @create 2018-10-03 7:42
 */
public class FastDFSTest {

    @Test
    public void testUpload() throws IOException, MyException {
        // 创建一个配置文件。文件名任意，内容就是Tracker服务器的地址
        // 使用全局变量加载配置文件
        ClientGlobal.init("D:\\develop\\IDEA\\workspace\\e3parent\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        // 创建一个TrackClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 通过TrackClient获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 创建一个StroageServer的引用，可以使null
        StorageServer storageServer = null;
        // 创建一个StorageClient ，参数需要TrackerServer和StorageServer
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        // 使用StorageClient上传文件
        String[] jpgs = storageClient.upload_appender_file("D:\\input\\d043ad4bd11373f02efb63adad0f4bfbfbed043f.jpg", "jpg", null);
        for (String jpg : jpgs) {
            System.out.println(jpg);
        }
    }

    @Test
    public void testFastDFSClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("D:\\develop\\IDEA\\workspace\\e3parent\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        String s = fastDFSClient.uploadFile("D:\\Download\\03-Linux基础进阶(开发人员必备)-3天\\Linux-Day02-权限、磁盘、用户、免密登录等命令\\HK001\\photo\\(m=e-yaaGqaa)(mh=_7pj83UNRmJnIfXT)original_1496492.jpg");
        System.out.println(s);
    }
}
