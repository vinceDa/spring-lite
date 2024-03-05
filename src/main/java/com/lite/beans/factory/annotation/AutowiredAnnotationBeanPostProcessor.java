package com.lite.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.lite.beans.BeansException;
import com.lite.beans.factory.*;
import com.lite.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.lite.core.convert.ConversionService;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author vince 2024/2/29 14:51
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 处理@Value 注解
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (Objects.nonNull(valueAnnotation)) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);

                // 类型转换
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if (Objects.nonNull(conversionService)) {
                    if (conversionService.canConvert(sourceType, targetType)) {
                        value = conversionService.convert(value, targetType);
                    }
                }

                BeanUtil.setFieldValue(bean, field.getName(), value);
            }

            Autowired autowired = field.getAnnotation(Autowired.class);
            if (Objects.nonNull(autowired)) {
                Class<?> fieldType = field.getType();
                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                String dependentBeanName;
                Object dependentBean;
                if (Objects.nonNull(qualifier)) {
                    dependentBeanName = qualifier.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }
}
