package ee.carlrobert.codegpt.client.Zhengyan.task;


import ee.carlrobert.codegpt.client.Zhengyan.IvyHttpClient;
import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.ZyLogDto;
import ee.carlrobert.codegpt.client.Zhengyan.config.IvyBackendConfig;
import ee.carlrobert.codegpt.client.Zhengyan.util.OkHttpClientUtil;
import ee.carlrobert.codegpt.conversations.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class ChatFeedbackTask implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatFeedbackTask.class);
    private boolean isSuccess;
    private Message message;

    public ChatFeedbackTask(Message message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    @Override
    public Object call() {
        if (!IvyBackendConfig.START_BACKEND_INTERACT) {
            return null;
        }
        OkHttpClientUtil instance = IvyHttpClient.getInstance();
        ZyLogDto zyLogDto = new ZyLogDto(message);
        if (!isSuccess) {
            zyLogDto.setFailMessage(message.getResponse());
        }
        try {
            LOGGER.info("Start sending feedback records({}): {}", IvyBackendConfig.CLIENT_LOG_URL, zyLogDto.toJson());
            String response = instance.postJson(IvyBackendConfig.CLIENT_LOG_URL, zyLogDto.toJson());
            LOGGER.info("Send feedback record successfully({}): {}", IvyBackendConfig.CLIENT_LOG_URL, response);
        } catch (Exception e) {
            LOGGER.error("Failed to send feedback record({}):{}", IvyBackendConfig.CLIENT_LOG_URL, e.getMessage());
        }

        return null;
    }
}

