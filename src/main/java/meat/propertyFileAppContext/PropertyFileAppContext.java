package meat.propertyFileAppContext;

import meat.Meat;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
// Контекст для настройки бинов при помощи проперти фаила АААА???? Без XML и гонфигов и регистрации!
public class PropertyFileAppContext extends GenericApplicationContext {
    public PropertyFileAppContext(String fileName) {
        PropertiesBeanDefinitionReader  reader = new PropertiesBeanDefinitionReader(this);
       int i =  reader.loadBeanDefinitions(fileName);
       System.out.println("Найдено "+ i + " бинов");
       refresh();
    }


}
