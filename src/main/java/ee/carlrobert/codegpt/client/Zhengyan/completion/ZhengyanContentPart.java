package ee.carlrobert.codegpt.client.Zhengyan.completion;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Base64;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZhengyanContentPart {

    private String text;
    private ZhengyanContentPart.Blob inlineData;

    public ZhengyanContentPart() {
    }

    public ZhengyanContentPart(String text) {
        this(text, null);
    }

    public ZhengyanContentPart(String text, ZhengyanContentPart.Blob inlineData) {
        this.text = text;
        this.inlineData = inlineData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZhengyanContentPart.Blob getInlineData() {
        return inlineData;
    }

    public void setInlineData(ZhengyanContentPart.Blob inlineData) {
        this.inlineData = inlineData;
    }

    /**
     * <a href="https://ai.google.dev/api/rest/v1/Content?authuser=1#Blob">Blob</a>.
     */
    public static class Blob {

        public static final Base64.Encoder ENCODER = Base64.getEncoder();

        private String mimeType;

        /**
         * base64 encoded.
         */
        private String data;

        public Blob() {
        }

        public Blob(String mimeType, byte[] data) {
            this.mimeType = mimeType;
            this.data = ENCODER.encodeToString(data);
        }

        public Blob(String mimeType, String base64EncodedData) {
            this.mimeType = mimeType;
            this.data = base64EncodedData;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
