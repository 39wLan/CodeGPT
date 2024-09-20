package ee.carlrobert.codegpt.conversations.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ee.carlrobert.llm.client.you.completion.YouSerpResult;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class Message {

  private final UUID id;
  private String prompt;
  private String response;
  private String userMessage;
  private List<YouSerpResult> serpResults;
  private List<String> referencedFilePaths;
  private @Nullable String imageFilePath;



  private String sessionId;
  private String actionType;
  private String startTime;
  private long startTmp;
  private String endTime;
  private long costTime;
  private int resStatus;
  private String codeLanguage;
  private String modelName;
  private String selectedText;
  private String prefix;
  private String context;
  private String smallestIntersectingMethod;
  private boolean isSuccess;

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public long getCostTime() {
    return costTime;
  }

  public void setCostTime(long costTime) {
    this.costTime = costTime;
  }

  public int getResStatus() {
    return resStatus;
  }

  public void setResStatus(int resStatus) {
    this.resStatus = resStatus;
  }

  public String getCodeLanguage() {
    return codeLanguage;
  }

  public void setCodeLanguage(String codeLanguage) {
    this.codeLanguage = codeLanguage;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getSelectedText() {
    return selectedText;
  }

  public void setSelectedText(String selectedText) {
    this.selectedText = selectedText;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getSmallestIntersectingMethod() {
    return smallestIntersectingMethod;
  }

  public void setSmallestIntersectingMethod(String smallestIntersectingMethod) {
    this.smallestIntersectingMethod = smallestIntersectingMethod;
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public long getStartTmp() {
    return startTmp;
  }

  public void setStartTmp(long startTmp) {
    this.startTmp = startTmp;
  }

  public Message(String prompt, String response) {
    this(prompt);
    this.response = response;
  }

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Message(@JsonProperty("prompt") String prompt) {
    this.id = UUID.randomUUID();
    this.prompt = prompt;
  }

  public UUID getId() {
    return id;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public List<YouSerpResult> getSerpResults() {
    return serpResults;
  }

  public void setSerpResults(List<YouSerpResult> serpResults) {
    this.serpResults = serpResults;
  }

  public List<String> getReferencedFilePaths() {
    return referencedFilePaths;
  }

  public void setReferencedFilePaths(List<String> referencedFilePaths) {
    this.referencedFilePaths = referencedFilePaths;
  }

  public @Nullable String getImageFilePath() {
    return imageFilePath;
  }

  public void setImageFilePath(@Nullable String imageFilePath) {
    this.imageFilePath = imageFilePath;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Message other)) {
      return false;
    }
    return Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, prompt);
  }
}
