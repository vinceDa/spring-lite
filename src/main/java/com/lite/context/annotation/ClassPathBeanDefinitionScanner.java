package com.lite.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.lite.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.support.BeanDefinitionRegistry;
import com.lite.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * @author vince 2024/2/28 10:41
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    private static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "com.lite.context.annotation.internalAutowiredAnnotationProcessor";

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                // 解析 bean 的作用域
                String beanScope = resolveBeanScope(candidate);
                if (StrUtil.isNotEmpty(beanScope)) {
                    candidate.setScope(beanScope);
                }

                // 生成 bean 的名称
                String beanName = determineBeanName(candidate);
                // 注册 beanDefinition
                registry.registerBeanDefinition(beanName, candidate);
            }
        }

        // 注册处理@Autowired 和@Value 注解的 BeanPostProcessor
        registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (Objects.nonNull(scope)) {
            return scope.value();
        }

        return StrUtil.EMPTY;
    }
}
