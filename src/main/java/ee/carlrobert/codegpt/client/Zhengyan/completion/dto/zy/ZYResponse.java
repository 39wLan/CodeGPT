package ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZYResponse {
    @JsonProperty("input")
    private String input;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("session_id")
    private String session_id;

    @JsonProperty("log_id")
    private String log_id;

    @JsonProperty("gpt_model")
    private GptModel gpt_model;

    @JsonProperty("is_end")
    private boolean is_end;

    public ZYResponse(){

    }

    public ZYResponse(String input, String answer, String session_id, String log_id, GptModel gpt_model, boolean is_end) {
        this.input = input;
        this.answer = answer;
        this.session_id = session_id;
        this.log_id = log_id;
        this.gpt_model = gpt_model;
        this.is_end = is_end;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public GptModel getGpt_model() {
        return gpt_model;
    }

    public void setGpt_model(GptModel gpt_model) {
        this.gpt_model = gpt_model;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }
}
