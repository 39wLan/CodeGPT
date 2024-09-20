package ee.carlrobert.codegpt.client.Zhengyan.completion;


import com.fasterxml.jackson.annotation.JsonInclude;
import ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel;
import ee.carlrobert.llm.completion.CompletionRequest;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZhengyanChatCompletionRequest implements CompletionRequest {

    private final String model;
    private final List<ZhengyanChatCompletionMessage> message;
    private final boolean stream;

    private ZhengyanChatCompletionRequest(ZhengyanChatCompletionRequest.Builder builder) {
        this.model = builder.model;
        this.message = builder.message;

        this.stream = builder.stream;
    }

    public List<ZhengyanChatCompletionMessage> getMessage() {
        return message;
    }

    public String getModel() {
        return model;
    }

    public boolean getStream() {
        return stream;
    }


    public static class Builder {

        private final List<ZhengyanChatCompletionMessage> message;
        private String model;
        private boolean stream = true;


        public Builder(List<ZhengyanChatCompletionMessage> message) {
            this.message = message;
        }

        public Builder(List<ZhengyanChatCompletionMessage> message,String model) {
            this.message = message;
            this.model=model;
        }

        public Builder(List<ZhengyanChatCompletionMessage> message,String model,boolean stream) {
            this.message = message;
            this.model=model;
            this.stream=stream;
        }

        public ZhengyanChatCompletionRequest.Builder setModel(ZhengyanModel model) {
            this.model = model.getCode();
            return this;
        }

        public ZhengyanChatCompletionRequest.Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public ZhengyanChatCompletionRequest.Builder setStream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public ZhengyanChatCompletionRequest build() {
            return new ZhengyanChatCompletionRequest(this);
        }
    }
}
