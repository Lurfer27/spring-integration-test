import com.yodel.model.BasicPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yodel.configuration","com.yodel.model"})
public class Application implements CommandLineRunner {

    @Autowired
    ConfigurableApplicationContext context;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(false).run(args);
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("ioc_basics.xml");
        //ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        for (String beanName : this.context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        BasicPOJO autowired = this.context.getBean("basicPOJO", BasicPOJO.class);
        System.out.println(autowired.getName());
        System.out.println(autowired.getLoggingColorRandomizer().randomColor());

        this.context.close();
    }
}
