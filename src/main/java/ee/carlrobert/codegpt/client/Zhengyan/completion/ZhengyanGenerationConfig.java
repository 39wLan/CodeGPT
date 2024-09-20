package ee.carlrobert.codegpt.client.Zhengyan.completion;


import java.util.List;


public class ZhengyanGenerationConfig {

    private final List<String> stopSequences;

    private final int maxOutputTokens;


    public ZhengyanGenerationConfig(ZhengyanGenerationConfig.Builder builder) {
        this.stopSequences = builder.stopSequences;

        this.maxOutputTokens = builder.maxOutputTokens;

    }

    public List<String> getStopSequences() {
        return stopSequences;
    }



    public int getMaxOutputTokens() {
        return maxOutputTokens;
    }


    public static class Builder {

        private List<String> stopSequences;

        private int maxOutputTokens = 4096;

        public ZhengyanGenerationConfig.Builder stopSequences(List<String> stopSequences) {
            this.stopSequences = stopSequences;
            return this;
        }

        public ZhengyanGenerationConfig.Builder maxOutputTokens(int maxOutputTokens) {
            this.maxOutputTokens = maxOutputTokens;
            return this;
        }

        public ZhengyanGenerationConfig build() {
            return new ZhengyanGenerationConfig(this);
        }
    }
}
