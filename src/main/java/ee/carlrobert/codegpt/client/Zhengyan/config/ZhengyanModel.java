package ee.carlrobert.codegpt.client.Zhengyan.config;


import java.util.Arrays;

import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanConfig.ZY_CHAT_GPT_URL;
import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanConfig.ZY_CODE_GPT_URL;

public enum ZhengyanModel {
    GPT_3_5_TURBO("gpt_3.5-turbo", "GPT-3.5-TURBO", 8192),
    ACodex("acodex","ACodex",8192),
    QWEN("g3-pro","千问72b",16384);


    private final String code;
    private final String description;
    private final int maxTokens;

    ZhengyanModel(String code, String description, int maxTokens) {
        this.code = code;
        this.description = description;
        this.maxTokens = maxTokens;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    @Override
    public String toString() {
        return description;
    }

    public static ZhengyanModel findByCode(String code) {
        return Arrays.stream(ZhengyanModel.values())
                .filter(item -> item.getCode().equals(code))
                .findFirst().orElse(ZhengyanModel.QWEN);
    }

    public static String getUrlByCode(String code){
        switch (code){
            case "gpt_3.5-turbo" ->{
                return ZY_CHAT_GPT_URL;
            }
            case "acodex" ->{
                return ZY_CODE_GPT_URL;
            }
            case "g3-pro" ->{
                return ZY_CHAT_GPT_URL;
            }
            default -> {
                return ZY_CHAT_GPT_URL;
            }
        }
    }
    public static String getModelTypeByCode(String code){
        switch (code){
            case "gpt_3.5-turbo" ->{
                return "AzureGPT";
            }
            case "acodex" ->{
                return "AzureCodeCushMan001";
            }
            case "g3-pro" ->{
                return "g3-pro";
            }
            default -> {
                return ZY_CHAT_GPT_URL;
            }
        }
    }
    public static int getTokenByCode(String code){
        switch (code){
            case "gpt_3.5-turbo" ->{
                return GPT_3_5_TURBO.maxTokens;
            }
            case "acodex" ->{
                return ACodex.maxTokens;
            }
            case "g3-pro" ->{
                return QWEN.maxTokens;
            }
            default -> {
                return GPT_3_5_TURBO.maxTokens;
            }
        }
    }
    public static boolean isZhengyanModel(String modelCode){
        switch (modelCode){
            case "gpt_3.5-turbo", "acodex", "g3-pro" ->{
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}


