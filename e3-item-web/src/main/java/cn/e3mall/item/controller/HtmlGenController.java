package cn.e3mall.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成静态页面测试Controller
 *
 * @author hoverkan
 * @create 2018-10-12 8:11
 */
@Controller
public class HtmlGenController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genhtml")
    @ResponseBody
    public String genHtml() throws Exception{
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //加载模板对象
        Template template = configuration.getTemplate("hello.ftl");
        //创建一个数据集
        Map data = new HashMap();
        data.put("hello", 123234);
        //指定文件的输出路径及文件名
        Writer out = new FileWriter(new File("D:/hello2.html"));
        // 输出文件
        template.process(data, out);
        //关闭流
        out.close();
        return "ok";
    }
}
