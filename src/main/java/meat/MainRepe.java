package meat;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//Запускатор кишков, и херрургических инструментов.
public class MainRepe {
    public static void main(String[] args) {
       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
    }
}
