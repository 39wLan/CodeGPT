package ee.carlrobert.codegpt.client.Zhengyan.completion.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedbackDto {
    private String chatId;
    private int feedbackType; // 用户操作：1-复制,2-新建文件,3-代码插入,4-文本替换
    private String feedbackTime;

    public FeedbackDto(String chartId, int feedbackType) {
        this.chatId = chartId;
        this.feedbackType = feedbackType;
        var current = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.feedbackTime = sdf.format(new Date(current));

    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(int feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
        return mapper.writeValueAsString(this);
    }

}
