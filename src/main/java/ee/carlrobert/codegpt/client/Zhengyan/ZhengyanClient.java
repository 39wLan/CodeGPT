package ee.carlrobert.codegpt.client.Zhengyan;


import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanConfig.ZY_CHAT_GPT_URL;
import static ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanConfig.ZY_GPT_KEY;
import static ee.carlrobert.codegpt.client.Zhengyan.util.ConvertUtil.generateSessionID;
import static ee.carlrobert.llm.client.DeserializationUtil.OBJECT_MAPPER;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import ee.carlrobert.codegpt.client.Zhengyan.completion.ZhengyanChatCompletionRequest;


import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy.ZYRequest;
import ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy.ZYResponse;
import ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel;

import ee.carlrobert.codegpt.client.Zhengyan.util.ConvertUtil;
import ee.carlrobert.codegpt.codecompletions.InfillRequestDetails;
import ee.carlrobert.codegpt.completions.CallParameters;

import ee.carlrobert.llm.client.google.completion.ApiResponseError;


import ee.carlrobert.llm.client.openai.completion.ErrorDetails;
import ee.carlrobert.llm.completion.CompletionEventListener;
import ee.carlrobert.llm.completion.CompletionEventSourceListener;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;

public class ZhengyanClient {

    private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");

    private final OkHttpClient httpClient;
    private final static String apiKey=ZY_GPT_KEY;

    public ZhengyanClient(OkHttpClient.Builder httpClientBuilder) {
        this.httpClient = httpClientBuilder.build();
    }


    public EventSource getChatCompletionAsync(
            ZhengyanChatCompletionRequest request,
            String model,
            CallParameters callParameters,
            CompletionEventListener<String> eventListener) {
        return EventSources.createFactory(httpClient)
                .newEventSource(buildPostRequest(request, model,callParameters, "streamGenerateContent", true),
                        getEventSourceListener(eventListener));
    }

    public EventSource getCodeCompletionAsync(
            InfillRequestDetails requestDetails,
            String model,
            CompletionEventListener<String> eventListener) {
        return EventSources.createFactory(httpClient)
                .newEventSource(buildPostRequest(requestDetails, model,  true),
                        getEventSourceListener(eventListener));
    }

    /**
     * <a
     * href="https://ai.google.dev/api/rest/v1/models/generateContent?authuser=1">GenerateContent</a>.
     */
//    public ZhengyanChatCompletionResponse getChatCompletion(ZhengyanChatCompletionRequest request,
//                                                            ZhengyanModel model) {
//        return getChatCompletion(request, model.getCode());
//    }

//    public ZhengyanChatCompletionResponse getChatCompletion(ZhengyanChatCompletionRequest request, String model) {
//        try (var response = httpClient.newCall(
//                buildPostRequest(request, model, "generateContent", false)).execute()) {
//            return DeserializationUtil.mapResponse(response, ZhengyanChatCompletionResponse.class);
//        } catch (IOException e) {
//            throw new RuntimeException(
//                    "Could not get llama completion for the given request:\n" + request, e);
//        }
//    }


    private Request buildPostRequest(ZhengyanChatCompletionRequest request, String model,CallParameters callParameters, String path,
                                     boolean stream) {
        try {
            ZYRequest zyRequest = ConvertUtil.ZYChatCompletionReqToZYRequest(request,callParameters);

            Request.Builder defaultRequestBuilder=new Request.Builder();
            Request.Builder builder = defaultRequestBuilder.
                    url(ZhengyanModel.getUrlByCode(model)).header("Authorization",ZY_GPT_KEY).header("Model-Type", ZhengyanModel.getModelTypeByCode(model))
                    .post(RequestBody.create(OBJECT_MAPPER.writeValueAsString(zyRequest),  MediaType.get("application/json; charset=utf-8")));
            return builder.build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Request buildPostRequest(InfillRequestDetails requestDetails, String model,
                                     boolean stream) {
        try {
            String session_id = generateSessionID();
            ZYRequest zyRequest = ConvertUtil.ZYCodeCompletionReqToZYRequest(requestDetails,session_id,stream);

            Request.Builder defaultRequestBuilder=new Request.Builder();
            Request.Builder builder = defaultRequestBuilder.
                    url(ZY_CHAT_GPT_URL).header("Authorization",ZY_GPT_KEY)
//                    .header("Model-Type",model)
                    .post(RequestBody.create(OBJECT_MAPPER.writeValueAsString(zyRequest),  MediaType.get("application/json; charset=utf-8")));
            return builder.build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    private Request.Builder defaultRequestBuilder(String url, boolean stream) {
        return new Request.Builder().url(url);
    }

    private CompletionEventSourceListener<String> getEventSourceListener(
            CompletionEventListener<String> eventListener) {
        return new CompletionEventSourceListener<>(eventListener) {
            @Override
            protected String getMessage(String data) {
                try {
                    var message=OBJECT_MAPPER.readValue(data, ZYResponse.class);
                    return message.getAnswer();
                } catch (JacksonException e) {
                    // ignore
                }
                return "";
            }

            @Override
            protected ErrorDetails getErrorDetails(String data) throws JsonProcessingException {
                var googleError = OBJECT_MAPPER.readValue(data, ApiResponseError.class).getError();
                return googleError == null ? null
                        : new ErrorDetails(googleError.getMessage(), googleError.getStatus(), null,
                        googleError.getCode());
            }
        };
    }

    public static class Builder {

        private String host = "https://zhengyan.sinosig.com/";

        private final String apiKey=ZY_GPT_KEY;


        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public ZhengyanClient build(OkHttpClient.Builder builder) {
            return new ZhengyanClient(builder);
        }

        public ZhengyanClient build() {
            return build(new OkHttpClient.Builder());
        }
    }
}
