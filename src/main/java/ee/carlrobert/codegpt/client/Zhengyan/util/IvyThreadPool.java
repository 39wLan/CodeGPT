package ee.carlrobert.codegpt.client.Zhengyan.util;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IvyThreadPool {
    public static final String THREAD_POOL_NAME = "常青藤线程池";
    /**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     */
    private static final int corePoolSize = Runtime.getRuntime().availableProcessors();

    private  IvyThreadPool(){}

    private static class Assistant {

        private static final ThreadPoolExecutor ivyThreadPool = new ExecutorsUtil(corePoolSize,2*corePoolSize,
                0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), THREAD_POOL_NAME);

    }

    public static ThreadPoolExecutor getIvyThreadPool(){
        return Assistant.ivyThreadPool;
    }

}
