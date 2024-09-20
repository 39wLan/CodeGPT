package ee.carlrobert.codegpt.settings.service.custom.template

enum class CustomServiceChatCompletionTemplate(
    val url: String,
    val headers: MutableMap<String, String>,
    val body: MutableMap<String, Any>
) {
    ANYSCALE(
        "https://api.endpoints.anyscale.com/v1/chat/completions",
        getDefaultHeadersWithAuthentication(),
        getDefaultBodyParams(
            mapOf(
                "model" to "mistralai/Mixtral-8x7B-Instruct-v0.1",
                "max_tokens" to 1024
            )
        )
    ),
    AZURE(
        "https://{your-resource-name}.openai.azure.com/openai/deployments/{deployment-id}/chat/completions?api-version=2023-05-15",
        getDefaultHeaders("api-key", "\$CUSTOM_SERVICE_API_KEY"),
        getDefaultBodyParams(emptyMap())
    ),
    DEEP_INFRA(
        "https://api.deepinfra.com/v1/openai/chat/completions",
        getDefaultHeadersWithAuthentication(),
        getDefaultBodyParams(
            mapOf(
                "model" to "meta-llama/Llama-2-70b-chat-hf",
                "max_tokens" to 1024
            )
        )
    ),
    ZY_CHAT_GPT(
            "https://zhengyan.sinosig.com/ai/ability/gpt/v1/chat",
            getZYHeaders("Authorization",ZY_GPT_KEY),
            getZYBodyParams(emptyMap())
    ),
    ZY_ACODEX_GPT(
            "https://zhengyan.sinosig.com/ai/ability/gpt/v1/chat",
            getZYHeaders("Authorization",ZY_GPT_KEY),
            getZYBodyParams(emptyMap())
    ),
    FIREWORKS(
        "https://api.fireworks.ai/inference/v1/chat/completions",
        getDefaultHeadersWithAuthentication(),
        getDefaultBodyParams(
            mapOf(
                "model" to "accounts/fireworks/config/llama-v2-7b-chat",
                "max_tokens" to 1024
            )
        )
    ),
    GROQ(
        "https://api.groq.com/openai/v1/chat/completions",
        getDefaultHeadersWithAuthentication(),
        getDefaultBodyParams(
            mapOf(
                "model" to "codellama-34b",
                "max_tokens" to 1024
            )
        )
    ),
    OPENAI(
        "https://api.openai.com/v1/chat/completions",
        getDefaultHeaders("Authorization", "Bearer \$CUSTOM_SERVICE_API_KEY"),
        getDefaultBodyParams(
            mapOf(
                "model" to "gpt-4",
                "max_tokens" to 1024
            )
        )
    ),
    PERPLEXITY(
        "https://api.perplexity.ai/chat/completions",
        getDefaultHeadersWithAuthentication(),
        getDefaultBodyParams(
            mapOf(
                "model" to "codellama",
                "max_tokens" to 1024
            )
        )
    ),
    TOGETHER(
        "https://api.together.xyz/v1/chat/completions",
        getDefaultHeaders("Authorization", "Bearer \$CUSTOM_SERVICE_API_KEY"),
        getDefaultBodyParams(
            mapOf(
                "model" to "deepseek-ai/deepseek-coder-33b-instruct",
                "max_tokens" to 1024
            )
        )
    ),
    OLLAMA(
        "http://localhost:11434/v1/chat/completions",
        getDefaultHeaders(),
        getDefaultBodyParams(mapOf("model" to "codellama"))
    ),
    LLAMA_CPP(
        "http://localhost:8080/v1/chat/completions",
        getDefaultHeaders(),
        getDefaultBodyParams(emptyMap())
    ),
    MISTRAL_AI(
        "https://api.mistral.ai/v1/chat/completions",
        getDefaultHeaders("Authorization", "Bearer \$CUSTOM_SERVICE_API_KEY"),
        getDefaultBodyParams(
            mapOf(
                "model" to "open-mistral-7b",
                "max_tokens" to 1024
            )
        )
    ),
    OPEN_ROUTER(
        "https://openrouter.ai/api/v1/chat/completions",
        getDefaultHeaders(
            mapOf(
                "Authorization" to "Bearer \$CUSTOM_SERVICE_API_KEY",
                "HTTP-Referer" to "https://plugins.jetbrains.com/plugin/21056-codegpt",
                "X-Title" to "CodeGPT"
            )
        ),
        getDefaultBodyParams(
            mapOf(
                "model" to "meta-llama/llama-3-8b-instruct:free",
                "max_tokens" to 1024
            )
        )
    );
}

private  const val ZY_GPT_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJidXNpbmVzc19uYW1lIjoi6ZuG5ZuiL" +
        "eWuueWZqOS6kSIsImtleV90eXBlIjpudWxsLCJncmFudF90eXBlIjoiYWNjZXNzX3Rva2VuIiwiYXBwSWQiOiJ" +
        "HMjk5IiwicmV0dXJuX1VybCI6bnVsbCwiYnVzaW5lc3NfY29kZSI6bnVsbCwibm90aWZ5X3VybC" +
        "I6bnVsbCwiZXhwIjoyMDAwNzA4NjczLCJjYWxsX3R5cGUiOm51bGx9.EQah93qqVJ2A86XoZfu4" +
        "J2Ze9BxPjPZ5K4pcQcoinof1U7xE7UGFvz0GvmHichaGQtX3REMUQKHzbzEzrZhxtQ"


private fun getDefaultHeadersWithAuthentication(): MutableMap<String, String> {
    return getDefaultHeaders("Authorization", "Bearer \$CUSTOM_SERVICE_API_KEY")
}

private fun getDefaultHeaders(): MutableMap<String, String> {
    return getDefaultHeaders(emptyMap())
}

private fun getDefaultHeaders(key: String, value: String): MutableMap<String, String> {
    return getDefaultHeaders(mapOf(key to value))
}

private fun getDefaultHeaders(additionalHeaders: Map<String, String>): MutableMap<String, String> {
    val defaultHeaders = mutableMapOf(
        "Content-Type" to "application/json",
        "X-LLM-Application-Tag" to "codegpt"
    )
    defaultHeaders.putAll(additionalHeaders)
    return defaultHeaders
}

fun getZYHeaders(key: String, value: String): MutableMap<String, String> {
    return getZYHeaders(mapOf(key to value))
}

private fun getZYHeaders(additionalHeaders: Map<String, String>): MutableMap<String, String> {
    val defaultHeaders = mutableMapOf(
            "Content-Type" to "application/json",
    )
    defaultHeaders.putAll(additionalHeaders)
    return defaultHeaders
}

private fun getDefaultBodyParams(additionalParams: Map<String, Any>): MutableMap<String, Any> {
    val defaultParams = mutableMapOf<String, Any>(
        "stream" to true,
        "messages" to "\$OPENAI_MESSAGES",
        "temperature" to 0.1
    )
    defaultParams.putAll(additionalParams)
    return defaultParams
}

fun getZYBodyParams(additionalParams: Map<String, Any>): MutableMap<String, Any> {
    val defaultParams = mutableMapOf<String, Any>(
    )
    defaultParams.putAll(additionalParams)
    return defaultParams
}
