package ee.carlrobert.codegpt.client.Zhengyan.completion;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("text")
public class ZhengyanMessageTextContent extends ZhengyanMessageContent {

    private String text;

    public ZhengyanMessageTextContent() {
    }

    public ZhengyanMessageTextContent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
