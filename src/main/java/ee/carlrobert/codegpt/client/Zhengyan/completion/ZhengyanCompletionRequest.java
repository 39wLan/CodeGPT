package ee.carlrobert.codegpt.client.Zhengyan.completion;

import ee.carlrobert.llm.completion.CompletionRequest;

import java.util.ArrayList;
import java.util.List;

public class ZhengyanCompletionRequest implements CompletionRequest {

    private final List<ZhengyanCompletionContent> contents;
    private final ZhengyanGenerationConfig generationConfig;

    public ZhengyanCompletionRequest(ZhengyanCompletionRequest.Builder builder) {
        this.contents = builder.contents;
        this.generationConfig = builder.generationConfig;
    }

    public List<ZhengyanCompletionContent> getContents() {
        return contents;
    }

    public ZhengyanGenerationConfig getGenerationConfig() {
        return generationConfig;
    }



    public static class Builder {

        private List<ZhengyanCompletionContent> contents;

        private ZhengyanGenerationConfig generationConfig = new ZhengyanGenerationConfig.Builder().build();

        public Builder(List<ZhengyanCompletionContent> contents) {
            this.contents = contents;
        }



        public ZhengyanCompletionRequest.Builder generationConfig(ZhengyanGenerationConfig generationConfig) {
            this.generationConfig = generationConfig;
            return this;
        }

        public ZhengyanCompletionRequest build() {
            return new ZhengyanCompletionRequest(this);
        }
    }
}
