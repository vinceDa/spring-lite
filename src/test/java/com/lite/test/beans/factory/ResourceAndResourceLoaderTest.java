package com.lite.test.beans.factory;

import cn.hutool.core.io.IoUtil;
import com.lite.core.io.*;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * 资源获取和资源加载器的测试类
 * @author vince 2024/2/4 18:19
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        // 加载 classpath 下的资源
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        assert resource instanceof ClassPathResource;
        InputStream is = resource.getInputStream();
        String content = IoUtil.readUtf8(is);
        assert "hello world".equals(content);

        // 加载文件系统资源
        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        assert resource instanceof FileSystemResource;
        is = resource.getInputStream();
        content = IoUtil.readUtf8(is);
        assert "hello world".equals(content);

        // 加载网络资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        assert resource instanceof UrlResource;
        is = resource.getInputStream();
        content = IoUtil.readUtf8(is);
        System.out.println(content);
    }

}
