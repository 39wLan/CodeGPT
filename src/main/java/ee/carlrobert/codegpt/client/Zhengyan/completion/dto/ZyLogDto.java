package ee.carlrobert.codegpt.client.Zhengyan.completion.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.application.ApplicationManager;
import ee.carlrobert.codegpt.client.Zhengyan.config.IdeaConfig;
import ee.carlrobert.codegpt.conversations.message.Message;
import ee.carlrobert.codegpt.settings.GeneralSettings;

public class ZyLogDto {
    private String modelName;
    private String chatId;
    private String clientVersion;
    private String sessionId;
    private String deptName;
    private String failMessage;
    private String startTime;
    private int clientType;
    private int resStatus;
    private String endTime;
    private String clientIp;
    private String domainAccount;
    private String askContent;
    private String codeLanguage;
    private String answerContent;
    private String subjectName;
    private String userName;
    private long costTime;
    private String pluginVersion;
    private String clientName;
    private String actionType;

    public ZyLogDto(Message message) {
        this.modelName = message.getModelName();
        this.chatId = message.getId().toString();
        this.sessionId = message.getSessionId();
        this.clientType = IdeaConfig.clientType; // IdeaConfig.ideaName;
        this.clientName = IdeaConfig.ideaName;
        this.clientVersion = IdeaConfig.ideaVersion;
        this.clientIp = IdeaConfig.localIp;
        this.pluginVersion = IdeaConfig.pluginVersion;
        this.startTime = message.getStartTime();
        this.resStatus = message.getResStatus();
        this.endTime = message.getEndTime();
        this.costTime = message.getCostTime();
        this.askContent = message.getPrompt();
        this.answerContent = message.getResponse();
        this.userName = ApplicationManager.getApplication().getService(GeneralSettings.class).getState().getDisplayName();
        this.codeLanguage = message.getCodeLanguage();
        this.actionType = message.getActionType();
    }

    public ZyLogDto(){}

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public int getResStatus() {
        return resStatus;
    }

    public void setResStatus(int resStatus) {
        this.resStatus = resStatus;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDomainAccount() {
        return domainAccount;
    }

    public void setDomainAccount(String domainAccount) {
        this.domainAccount = domainAccount;
    }

    public String getAskContent() {
        return askContent;
    }

    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long
    getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
        return mapper.writeValueAsString(this);
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
