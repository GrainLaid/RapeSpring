package meat.hellSpring;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

//Это фигня меняет все классы перед созданием фабрикой бинов на те что придумал Гендальф!
public class HellInSpringBeanFactoryBeanpostProcesser implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      String[] names =  beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
           String beanClassName =  beanDefinition.getBeanClassName();
            try {
                Class<?> beanClass =  Class.forName(beanClassName);
                HellInSpring annotation =  beanClass.getAnnotation(HellInSpring.class);
                if(annotation!=null) {
                    beanDefinition.setBeanClassName(annotation.hellNewImpl().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
