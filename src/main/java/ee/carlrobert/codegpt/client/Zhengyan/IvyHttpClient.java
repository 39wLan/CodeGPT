package ee.carlrobert.codegpt.client.Zhengyan;

import ee.carlrobert.codegpt.client.Zhengyan.config.IvyBackendConfig;
import ee.carlrobert.codegpt.client.Zhengyan.util.OkHttpClientUtil;

public class IvyHttpClient {
    public static OkHttpClientUtil ivyHttpClient;
    private static final byte[] LOCKER = new byte[0];

    /**
     * 单例模式获取 NetUtils
     *
     * @return {@link OkHttpClientUtil}
     */
    public static OkHttpClientUtil getInstance() {
        if (ivyHttpClient == null) {
            synchronized (LOCKER) {
                if (ivyHttpClient == null) {
                    OkHttpClientUtil.initHeaders(IvyBackendConfig.TOKEN);
                    ivyHttpClient = new OkHttpClientUtil();
                }
            }
        }
        return ivyHttpClient;
    }
}
