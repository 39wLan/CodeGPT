package ee.carlrobert.codegpt.client.Zhengyan.completion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ZhengyanMessageTextContent.class, name = "text")})
public abstract class ZhengyanMessageContent {
}
