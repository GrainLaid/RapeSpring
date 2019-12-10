package meat;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;


//Учим бин пост процессор нвым приколюхам!
public class RepeatScreamRandomAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //учим новую анатацию работать!
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            RepeatScreamRandom annotation = field.getAnnotation(RepeatScreamRandom.class);
            if (annotation != null){
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int i = min+random.nextInt(max-min);
            //Присовываем полям!
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, i);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}


