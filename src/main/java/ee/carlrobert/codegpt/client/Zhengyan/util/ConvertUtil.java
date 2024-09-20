package ee.carlrobert.codegpt.client.Zhengyan.util;

import ee.carlrobert.codegpt.client.Zhengyan.completion.ZhengyanChatCompletionMessage;
import ee.carlrobert.codegpt.client.Zhengyan.completion.ZhengyanChatCompletionRequest;
import ee.carlrobert.codegpt.client.Zhengyan.completion.ZhengyanChatCompletionStandardMessage;
import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy.ExtModel;
import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy.UserInfo;
import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy.ZYRequest;
import ee.carlrobert.codegpt.codecompletions.InfillPromptTemplate;
import ee.carlrobert.codegpt.codecompletions.InfillRequestDetails;
import ee.carlrobert.codegpt.completions.CallParameters;
import ee.carlrobert.codegpt.completions.ConversationType;
import ee.carlrobert.codegpt.conversations.Conversation;
import ee.carlrobert.codegpt.conversations.message.Message;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel.GPT_3_5_TURBO;
import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel.getTokenByCode;
import static ee.carlrobert.codegpt.completions.CompletionRequestProvider.ZY_CODE_PROMPT;
import static ee.carlrobert.codegpt.completions.llama.LlamaModel.CODE_QWEN;


public class ConvertUtil {

    public static final String zySessionIdPrefix = "ideaplugin";

    public static ZYRequest ZYChatCompletionReqToZYRequest(ZhengyanChatCompletionRequest request, Conversation conversation){
        String session_id = conversation.getSession_id();
        String input = findLastMessageByUser(request.getMessage());
        UserInfo userInfo = getDefaultUserInfo();
        ExtModel extModel = new ExtModel();
        extModel.setStream(true);
        extModel.setTemperature(1);
        extModel.setMax_tokens(getTokenByCode(request.getModel()));
        ZYRequest zyRequest = new ZYRequest(session_id,input,userInfo,extModel);
        return zyRequest;
    }

    public static ZYRequest ZYChatCompletionReqToZYRequest(ZhengyanChatCompletionRequest request, CallParameters callParameters){
        Conversation conversation = callParameters.getConversation();
        String session_id = conversation.getSession_id();

        Message message = callParameters.getMessage();
        message.setSessionId(session_id);
        message.setModelName(conversation.getModel());
        if(StringUtils.isEmpty(message.getActionType())){
            message.setActionType(callParameters.getConversationType().toString());
        }
        message.setStartTime(getCurrentTime());
        message.setStartTmp(System.currentTimeMillis());

        String input = findLastMessageByUser(request.getMessage());
        UserInfo userInfo = getDefaultUserInfo();
        ExtModel extModel = new ExtModel();
        extModel.setStream(true);
        extModel.setTemperature(1);
        extModel.setMax_tokens(getTokenByCode(request.getModel()));
        ZYRequest zyRequest = new ZYRequest(session_id,input,userInfo,extModel);
        return zyRequest;
    }


    public static ZYRequest ZYCodeCompletionReqToZYRequest(InfillRequestDetails requestDetails, String session_id,boolean stream){
        String input = buildCodePropmt(requestDetails);
        UserInfo userInfo = getDefaultUserInfo();

        ExtModel extModel = new ExtModel();
        extModel.setStream(stream);
        extModel.setTemperature(0.1);
        extModel.setMax_tokens(96);

        ZYRequest zyRequest = new ZYRequest(session_id,input,userInfo,extModel);
        return zyRequest;
    }

    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date(System.currentTimeMillis()));
    }




    public static String buildCodePropmt(InfillRequestDetails requestDetails){
        String promptTemplate =InfillPromptTemplate.CODE_QWEN.buildPrompt("",requestDetails.getPrefix());
//        System.out.println("prompt-start");
//        System.out.println(promptTemplate);
//        System.out.println("prompt-end");
        return promptTemplate;
    }
    public static String findLastMessageByUser(List<ZhengyanChatCompletionMessage> messages){
        for (int i = messages.size() - 1; i >= 0; i--) {
            ZhengyanChatCompletionStandardMessage message = (ZhengyanChatCompletionStandardMessage) messages.get(i);
            // 检查消息角色是否为用户
            if (message.getRole().equals("user")) {
                // 返回用户消息的内容
                return message.getContent();
            }
        }
        // 如果没有找到用户消息，则返回空字符串
        return "";
    }

    public static UserInfo getDefaultUserInfo(){
        return new UserInfo("ivy",
        "常青藤",
        "技术架构部",
        "阳光数智科技有限责任公司");
    }

     public static String generateSessionID() {
          String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
          StringBuilder sb = new StringBuilder();
          Random random = new Random();
          sb.append(zySessionIdPrefix);
          for (int i = 0; i < 16; i++) {
               sb.append(characters.charAt(random.nextInt(characters.length())));
          }
          return sb.toString();
     }
}


