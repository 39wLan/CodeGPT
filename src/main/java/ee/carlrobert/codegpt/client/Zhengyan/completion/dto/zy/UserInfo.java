package ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy;

public  class UserInfo {

    private String user_id;
    private String user_name;
    private String user_dept_name;
    private String user_company;

    public UserInfo(){

    }

    public UserInfo(String user_id,String user_name,String user_dept_name,String user_company){
        this.user_id=user_id;
        this.user_name=user_name;
        this.user_dept_name=user_dept_name;
        this.user_company=user_company;
    }

    // Getters and Setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_dept_name() {
        return user_dept_name;
    }

    public void setUser_dept_name(String user_dept_name) {
        this.user_dept_name = user_dept_name;
    }

    public String getUser_company() {
        return user_company;
    }

    public void setUser_company(String user_company) {
        this.user_company = user_company;
    }

    // Additional methods like toString(), equals(), hashCode() can be added here.
}

