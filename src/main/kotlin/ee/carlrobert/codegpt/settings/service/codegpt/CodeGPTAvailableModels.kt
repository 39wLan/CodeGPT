package ee.carlrobert.codegpt.settings.service.codegpt

import ee.carlrobert.codegpt.Icons
import ee.carlrobert.llm.client.codegpt.PricingPlan
import ee.carlrobert.llm.client.codegpt.PricingPlan.*
import javax.swing.Icon

object CodeGPTAvailableModels {

    @JvmStatic
    fun getToolWindowModels(pricingPlan: PricingPlan?): List<CodeGPTModel> {
        val anonymousModels = BASE_CHAT_MODELS + CodeGPTModel(
            "Llama 3 (8B) - FREE",
            "llama-3-8b",
            Icons.Meta,
            ANONYMOUS
        )
        if (pricingPlan == null) {
            return anonymousModels
        }
        return when (pricingPlan) {
            ANONYMOUS -> anonymousModels

            FREE -> BASE_CHAT_MODELS + listOf(
                CodeGPTModel("Code Llama (70B)", "codellama:chat", Icons.Meta, FREE),
                CodeGPTModel("Mixtral (8x22B)", "mixtral-8x22b", Icons.CodeGPTModel, FREE),
                CodeGPTModel(
                    "DeepSeek Coder (33B)",
                    "deepseek-coder-33b",
                    Icons.CodeGPTModel,
                    FREE
                ),
                CodeGPTModel("WizardLM-2 (8x22B)", "wizardlm-2-8x22b", Icons.CodeGPTModel, FREE),
                CodeGPTModel("GPT_3.5 Turbo", "gpt_3.5-turbo", Icons.Zhengyan, FREE),
                CodeGPTModel("ACodex", "acodex", Icons.Zhengyan, FREE),
                CodeGPTModel("千问72b","g3-pro",Icons.Zhengyan,FREE)
            )

            else -> BASE_CHAT_MODELS
        }
    }

    @JvmStatic
    val BASE_CHAT_MODELS: List<CodeGPTModel> = listOf(
        CodeGPTModel("GPT-4o", "gpt-4o", Icons.OpenAI, INDIVIDUAL),
        CodeGPTModel("GPT-3.5 Turbo", "gpt-3.5-turbo", Icons.OpenAI, INDIVIDUAL),
        CodeGPTModel("Claude 3 Opus", "claude-3-opus", Icons.Anthropic, INDIVIDUAL),
        CodeGPTModel("Claude 3.5 Sonnet", "claude-3.5-sonnet", Icons.Anthropic, INDIVIDUAL),
        CodeGPTModel("Claude 3 Sonnet", "claude-3-sonnet", Icons.Anthropic, INDIVIDUAL),
        CodeGPTModel("DBRX", "dbrx", Icons.Databricks, INDIVIDUAL),
        CodeGPTModel("Llama 3 (70B)", "llama-3-70b", Icons.Meta, FREE),
    )

    @JvmStatic
    val ALL_ZY_MODELS: List<CodeGPTModel> = listOf(
            CodeGPTModel("GPT_3.5 Turbo", "gpt_3.5-turbo", Icons.Zhengyan, FREE),
            CodeGPTModel("ACodex", "acodex", Icons.Zhengyan, FREE),
            CodeGPTModel("千问72b","g3-pro",Icons.Zhengyan,FREE)
    )

    @JvmStatic
    val ALL_CHAT_MODELS: List<CodeGPTModel> = BASE_CHAT_MODELS + ALL_ZY_MODELS + listOf(
        CodeGPTModel("Llama 3 (8B) - FREE", "llama-3-8b", Icons.Meta, ANONYMOUS),
        CodeGPTModel("Code Llama (70B)", "codellama:chat", Icons.Meta, FREE),
        CodeGPTModel("Mixtral (8x22B)", "mixtral-8x22b", Icons.CodeGPTModel, FREE),
        CodeGPTModel("DeepSeek Coder (33B)", "deepseek-coder-33b", Icons.CodeGPTModel, FREE),
        CodeGPTModel("WizardLM-2 (8x22B)", "wizardlm-2-8x22b", Icons.CodeGPTModel, FREE)
    )



    @JvmStatic
    val CODE_MODELS: List<CodeGPTModel> = listOf(
        CodeGPTModel("GPT-3.5 Turbo Instruct", "gpt-3.5-turbo-instruct", Icons.OpenAI, INDIVIDUAL),
        CodeGPTModel("StarCoder (16B)", "starcoder-16b", Icons.CodeGPTModel, FREE),
        CodeGPTModel("StarCoder (7B) - FREE", "starcoder-7b", Icons.CodeGPTModel, FREE),
        CodeGPTModel("GPT_3.5 Turbo", "gpt_3.5-turbo", Icons.Zhengyan, FREE),
        CodeGPTModel("ACodex", "acodex", Icons.Zhengyan, FREE),
        CodeGPTModel("千问72b","g3-pro",Icons.Zhengyan,FREE),
        CodeGPTModel("Code Llama (70B)", "codellama:code", Icons.CodeGPTModel, FREE),
        CodeGPTModel("Code Llama Python (70B)", "codellama-python", Icons.CodeGPTModel, FREE),
        CodeGPTModel("WizardCoder Python (34B)", "wizardcoder-python", Icons.CodeGPTModel, FREE),
        CodeGPTModel("Phind Code LLaMA v2 (34B)", "phind-codellama", Icons.CodeGPTModel, FREE)
    )

    @JvmStatic
    fun findByCode(code: String?): CodeGPTModel? {
        return ALL_CHAT_MODELS.union(CODE_MODELS).firstOrNull { it.code == code }
    }
}

data class CodeGPTModel(
    val name: String,
    val code: String,
    val icon: Icon,
    val individual: PricingPlan
)
