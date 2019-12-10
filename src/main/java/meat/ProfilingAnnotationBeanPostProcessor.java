package meat;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<String, Class>();
    private ProfilingController controller = new ProfilingController();


    //регестрируем мбин
    public ProfilingAnnotationBeanPostProcessor() throws Exception {
      MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling","name","flagControlle"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //Кидаем в мапу классы с аннотацией
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        //Достаём из мапы класс с анотацией и генерим для неё прокси класс на лету!
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //если флажок включен профилировать фигнё
                    if (controller.isEnabled()) {
                        System.out.println("Создаю на летку прокси класс который принимает существующие объекты и меняю их логику!");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println(after - before);
                        System.out.println("Закончил маяться фигнёй");

                        return retVal;
                        //иначе ничего не знаю ничего не видел...
                    }else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}


