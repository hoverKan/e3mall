package cn.e3mall.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author hoverkan
 * @create 2018-10-11 11:06
 */
public class TestFreeMarker {

    @Test
    public void getFile() throws Exception {
        //1、创建一个Configuration对象，直接new一个对象，构造方法的参数就是freemarker对应的版本号
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2、设置模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\develop\\IDEA\\workspace\\e3parent\\e3-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        //3、设置模板文件使用的字符集，一般就是utf-8
        configuration.setDefaultEncoding("UTF-8");
        //4、加载一个模板，创建一个模板对象
        //Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");
        //5、创建一个模板使用的数据集，可以使pojo也可以是map。一般是map
        Map dataModel = new HashMap();
        // 向数据集中添加数据
        dataModel.put("hello", "This is my first freemarker test !");

        // 创建一个pojo对象
        Student student = new Student(1,"HoverKan",20,"回龙观");
        dataModel.put("student",student);

        // 添加一个list
        List<Student> studentList = Arrays.asList(
                new Student(1,"HoverKan1",20,"回龙观"),
                new Student(2,"HoverKan2",21,"回龙观"),
                new Student(3,"HoverKan3",22,"回龙观"),
                new Student(4,"HoverKan4",23,"回龙观"),
                new Student(5,"HoverKan5",24,"回龙观"),
                new Student(6,"HoverKan6",25,"回龙观"),
                new Student(7,"HoverKan7",26,"回龙观")
        );

        dataModel.put("studentList",studentList);

        // 添加一个日期
        dataModel.put("date", new Date());

        // null值处理
        dataModel.put("val", "Hover-Kan");

        //6、创建一个Writer对象，一般创建一 FileWriter 对象，指定生成的文件名
        Writer out = new FileWriter(new File("D:/student.html"));
        //7、调用模板对象的process方法输出文件
        template.process(dataModel, out);
        //8、关闭流
        out.close();
    }

}
