package ee.carlrobert.codegpt.client.Zhengyan.completion;


import com.fasterxml.jackson.annotation.JsonInclude;
import ee.carlrobert.llm.client.google.completion.GoogleContentPart;


import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZhengyanCompletionContent {

    private List<ZhengyanContentPart> parts;
    private String role;

    public ZhengyanCompletionContent() {
    }

    public ZhengyanCompletionContent(List<String> texts) {
        this(null, texts);
    }

    public ZhengyanCompletionContent(String role, List<String> texts) {
        this.parts = texts.stream().map(ZhengyanContentPart::new).collect(Collectors.toList());
        this.role = role;
    }

    public ZhengyanCompletionContent(List<ZhengyanContentPart> parts, String role) {
        this.parts = parts;
        this.role = role;
    }

    public List<ZhengyanContentPart> getParts() {
        return parts;
    }

    public void setParts(
            List<ZhengyanContentPart> parts) {
        this.parts = parts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

