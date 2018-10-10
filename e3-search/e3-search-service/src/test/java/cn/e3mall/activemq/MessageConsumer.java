package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hoverkan
 * @create 2018-10-10 14:05
 */
public class MessageConsumer {
    @Test
    public void msgConsumer() throws Exception{
        // 初始化Spring容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        // 等待
        System.in.read();
    }
}
