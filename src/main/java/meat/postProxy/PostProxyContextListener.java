package meat.postProxy;

import meat.postProxy.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

public class PostProxyContextListener implements ApplicationListener<ContextRefreshedEvent> {
    //прикольная херь инжектю спринговую фабрику в който класс. по сути спринк встовляю в спринг
    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            try {
               Class<?> originalClass = Class.forName(originalClassName);
               Method[] methods =  originalClass.getMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(PostProxy.class)){
                        //получаю метод текущего класса из бина оригинального
                      Object bean = context.getBean(name);
                     Method wowMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                     wowMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
