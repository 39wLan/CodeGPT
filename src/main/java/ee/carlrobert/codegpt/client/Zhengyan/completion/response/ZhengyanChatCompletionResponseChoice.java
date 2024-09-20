package ee.carlrobert.codegpt.client.Zhengyan.completion.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZhengyanChatCompletionResponseChoice {

    private final ZhengyanChatCompletionResponseChoiceDelta delta;
    private final ZhengyanChatCompletionResponseChoiceDelta message;
    private final String finishReason;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ZhengyanChatCompletionResponseChoice(
            @JsonProperty("delta") ZhengyanChatCompletionResponseChoiceDelta delta,
            @JsonProperty("message") ZhengyanChatCompletionResponseChoiceDelta message,
            @JsonProperty("finish_reason") String finishReason) {
        this.delta = delta;
        this.message = message;
        this.finishReason = finishReason;
    }

    public ZhengyanChatCompletionResponseChoiceDelta getDelta() {
        return delta;
    }

    public ZhengyanChatCompletionResponseChoiceDelta getMessage() {
        return message;
    }

    public String getFinishReason() {
        return finishReason;
    }
}
