package ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GptModel {

    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private String value;


    public GptModel() {
    }

    public GptModel(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
