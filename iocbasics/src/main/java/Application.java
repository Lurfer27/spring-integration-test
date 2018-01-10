import com.yodel.configuration.Config;
import com.yodel.model.BasicPOJO;
import com.yodel.model.LoggingColorRandomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("ioc_basics.xml");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        //System.out.println(context.getBean("logFile", File.class).getName());
        //System.out.println(context.getBean("loggingColorRandomizer", LoggingColorRandomizer.class).randomColor());
        BasicPOJO autowired = context.getBean("basicPOJO", BasicPOJO.class);
        System.out.println(autowired.getName());
        System.out.println(autowired.getLoggingColorRandomizer().randomColor());

        context.close();
    }
}
