package ee.carlrobert.codegpt.client.Zhengyan.completion;

public class ZhengyanChatCompletionStandardMessage implements ZhengyanChatCompletionMessage {

    private final String content;
    private final String role;

    public ZhengyanChatCompletionStandardMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
