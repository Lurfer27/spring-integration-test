import com.yodel.model.BasicPOJO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ioc_basics.xml");

        BasicPOJO autowired = ctx.getBean("basicPOJO", BasicPOJO.class);
        System.out.println(autowired.getName());
        System.out.println(autowired.getLoggingColorRandomizer());

        ctx.close();
    }
}
