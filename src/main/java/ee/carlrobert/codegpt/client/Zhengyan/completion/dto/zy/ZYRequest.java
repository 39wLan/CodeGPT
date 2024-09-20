package ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy;

public class ZYRequest {

    private String session_id;
    private String input;
    private UserInfo user_info;
    private ExtModel ext_model;

    public ZYRequest(String session_id, String input, UserInfo user_info, ExtModel ext_model) {
        this.session_id = session_id;
        this.input = input;
        this.user_info = user_info;
        this.ext_model = ext_model;
    }

    // Getters and Setters
    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public ExtModel getExt_model() {
        return ext_model;
    }

    public void setExt_model(ExtModel ext_model) {
        this.ext_model = ext_model;
    }

    // Additional methods like toString(), equals(), hashCode() can be added here.
}
