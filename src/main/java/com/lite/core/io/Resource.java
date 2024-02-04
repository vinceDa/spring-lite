package com.lite.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源的抽象和访问接口
 * @author vince 2024/2/4 17:54
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
