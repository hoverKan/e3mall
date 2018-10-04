package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传的Controller
 *
 * @author hoverkan
 * @create 2018-10-03 9:06
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value="/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
       try{
           // 把图片上传到图片服务器
           FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
           // 取文件扩展名
           String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
           // 得到一个图片的地址和文件名
           String url = fastDFSClient.uploadFile(uploadFile.getBytes(), ext);
           // 补充为完整的url
           url = IMAGE_SERVER_URL + url;
           // 封装到Map中返回
           Map result = new HashMap();
           result.put("error",0);
           result.put("url", url);
           return JsonUtils.objectToJson(result);
       } catch (Exception e){
           e.printStackTrace();
           Map result = new HashMap();
           result.put("error",1);
           result.put("message", "图片上传失败!");
           return JsonUtils.objectToJson(result);
       }

    }
}
