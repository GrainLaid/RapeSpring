package meat;

import meat.propertyFileAppContext.PropertyFileAppContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Запускатор кишков, и херрургических инструментов.
public class MainRepe {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
        context.getBean(Meat.class).sayWhatAreYouDoing();
//вызывает обект с проперти настроиками!
        PropertyFileAppContext context1 = new PropertyFileAppContext("contex.properties");
        context1.getBean(Meat.class).sayWhatAreYouDoing();


    }

}
