package com.lite.test.beans.factory;

import com.lite.test.beans.factory.common.CustomBeanFactoryPostProcessor;
import com.lite.beans.factory.xml.XmlBeanDefinitionReader;
import com.lite.test.beans.factory.bean.Car;
import com.lite.test.beans.factory.bean.Person;
import com.lite.test.beans.factory.common.CustomerBeanPostProcessor;
import com.lite.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/6 12:05
 */
public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 在所有 BeanDefinition 加载完成后，但在 bean 实例化之前，修改 BeanDefinition 的属性值
        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        assert "ohyoung".equals(person.getName());
    }

    @Test
    public void testBeanPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 添加 bean 实例化后的处理器
        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);

        assert "宝马".equals(car.getBrand());
    }
}
