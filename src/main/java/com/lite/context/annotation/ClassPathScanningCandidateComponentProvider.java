package com.lite.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author vince 2024/2/28 10:30
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String backPackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 扫描有 com.lite.stereotype.Component 注解的类
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(backPackage, Component.class);
        for (Class<?> clazz : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }

}
