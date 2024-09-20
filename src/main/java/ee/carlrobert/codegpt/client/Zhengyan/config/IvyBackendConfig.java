package ee.carlrobert.codegpt.client.Zhengyan.config;

public class IvyBackendConfig {
    public static final Boolean START_BACKEND_INTERACT = true;

    // 生产
    public static final String BASE_URL = "http://ivy.sinosig.com:9091";

    // 测试
//    public static final String BASE_URL = "http://10.7.137.171:9091";
    public static final String TOKEN = "";
    public static final String FALLBACK_URL = BASE_URL + "/ivy/client/feedback";
    public static final String CLIENT_LOG_URL = BASE_URL + "/ivy/client/zy_log";
}
