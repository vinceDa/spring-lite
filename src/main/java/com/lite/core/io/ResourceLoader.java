package com.lite.core.io;

/**
 * 资源加载器的抽象
 * @author vince 2024/2/4 18:10
 */
public interface ResourceLoader {

    Resource getResource(String location);

}
