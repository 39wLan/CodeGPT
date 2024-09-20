package ee.carlrobert.codegpt.completions;

public enum ConversationType {
  CUSTOM_PROMPT,
  DEFAULT,
  EDITOR_ACTION,
  FIX_COMPILE_ERRORS,
  MULTI_FILE,
  INLINE_COMPLETION,
  ZY_CHAT;

  @Override
  public String toString() {
    switch (name()){
      case "ZY_CHAT":
        return "文本问答";
      default:
        return name();
    }
  }
}
