import com.yodel.model.BasicPOJO;
import com.yodel.model.ColorEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ioc_basics.xml");
        BasicPOJO red = ctx.getBean("constructor-setup", BasicPOJO.class);
        BasicPOJO mario = ctx.getBean("no-args", BasicPOJO.class);
        System.out.println(red.getName() + ":" + red.getColor());
        System.out.println(mario.getName() + ":" + mario.getColor());
    }
}
