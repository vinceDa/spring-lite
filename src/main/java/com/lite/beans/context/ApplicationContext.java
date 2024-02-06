package com.lite.beans.context;

import com.lite.beans.factory.HierarchicalBeanFactory;
import com.lite.beans.factory.ListableBeanFactory;
import com.lite.core.io.ResourceLoader;

/**
 * 应用上下文，面向 spring 的使用者
 * @author vince 2024/2/6 16:46
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
