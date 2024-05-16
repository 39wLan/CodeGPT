package ee.carlrobert.codegpt.completions.llama;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

import ee.carlrobert.codegpt.codecompletions.InfillPromptTemplate;
import ee.carlrobert.codegpt.completions.HuggingFaceModel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;

public enum LlamaModel {
  CODE_LLAMA(
      "Code Llama",
      "Code Llama is a family of large language models for code based on Llama 2 "
          + "providing state-of-the-art performance among open models, infilling capabilities, "
          + "support for large input contexts, and zero-shot instruction following ability for "
          + "programming tasks.",
      PromptTemplate.LLAMA,
      InfillPromptTemplate.CODE_LLAMA,
      List.of(
          HuggingFaceModel.CODE_LLAMA_7B_Q3,
          HuggingFaceModel.CODE_LLAMA_7B_Q4,
          HuggingFaceModel.CODE_LLAMA_7B_Q5,
          HuggingFaceModel.CODE_LLAMA_13B_Q3,
          HuggingFaceModel.CODE_LLAMA_13B_Q4,
          HuggingFaceModel.CODE_LLAMA_13B_Q5,
          HuggingFaceModel.CODE_LLAMA_34B_Q3,
          HuggingFaceModel.CODE_LLAMA_34B_Q4,
          HuggingFaceModel.CODE_LLAMA_34B_Q5)
  ),
  CODE_BOOGA(
      "CodeBooga",
      "CodeBooga is a high-performing code instruct model created by merging two existing"
          + " code models: "
          + "<ol><li>Phind-CodeLlama-34B-v2</li><li>WizardCoder-Python-34B-V1.0</li></ol>",
      PromptTemplate.ALPACA,
      List.of(
          HuggingFaceModel.CODE_BOOGA_34B_Q3,
          HuggingFaceModel.CODE_BOOGA_34B_Q4,
          HuggingFaceModel.CODE_BOOGA_34B_Q5)),
  DEEPSEEK_CODER(
      "Deepseek Coder",
      "Deepseek Coder is composed of a series of code language models, each trained "
          + "from scratch on 2T tokens, with a composition of 87% code and 13% natural language "
          + "in both English and Chinese. It achieves state-of-the-art performance among "
          + "open-source code models on multiple programming languages and various benchmarks.",
      PromptTemplate.ALPACA,
      InfillPromptTemplate.DEEPSEEK_CODER,
      List.of(
          HuggingFaceModel.DEEPSEEK_CODER_1_3B_Q3,
          HuggingFaceModel.DEEPSEEK_CODER_1_3B_Q4,
          HuggingFaceModel.DEEPSEEK_CODER_1_3B_Q5,
          HuggingFaceModel.DEEPSEEK_CODER_6_7B_Q3,
          HuggingFaceModel.DEEPSEEK_CODER_6_7B_Q4,
          HuggingFaceModel.DEEPSEEK_CODER_6_7B_Q5,
          HuggingFaceModel.DEEPSEEK_CODER_33B_Q3,
          HuggingFaceModel.DEEPSEEK_CODER_33B_Q4,
          HuggingFaceModel.DEEPSEEK_CODER_33B_Q5)),
  PHIND_CODE_LLAMA(
      "Phind Code Llama",
      "This model is fine-tuned from Phind-CodeLlama-34B-v1 on an additional 1.5B tokens "
          + "high-quality programming-related data, achieving 73.8% pass@1 on HumanEval. "
          + "It's the current state-of-the-art amongst open-source models.",
      PromptTemplate.ALPACA,
      List.of(
          HuggingFaceModel.PHIND_CODE_LLAMA_34B_Q3,
          HuggingFaceModel.PHIND_CODE_LLAMA_34B_Q4,
          HuggingFaceModel.PHIND_CODE_LLAMA_34B_Q5)),
  WIZARD_CODER_PYTHON(
      "WizardCoder - Python",
      "WizardCoder, a Code Evol-Instruct fine-tuned Code LLM, which achieves "
          + "the 73.2 pass@1 and surpasses GPT4 (2023/03/15), ChatGPT-3.5, "
          + "and Claude2 on the HumanEval Benchmarks.",
      PromptTemplate.ALPACA,
      List.of(
          HuggingFaceModel.WIZARD_CODER_PYTHON_7B_Q3,
          HuggingFaceModel.WIZARD_CODER_PYTHON_7B_Q4,
          HuggingFaceModel.WIZARD_CODER_PYTHON_7B_Q5,
          HuggingFaceModel.WIZARD_CODER_PYTHON_13B_Q3,
          HuggingFaceModel.WIZARD_CODER_PYTHON_13B_Q4,
          HuggingFaceModel.WIZARD_CODER_PYTHON_13B_Q5,
          HuggingFaceModel.WIZARD_CODER_PYTHON_34B_Q3,
          HuggingFaceModel.WIZARD_CODER_PYTHON_34B_Q4,
          HuggingFaceModel.WIZARD_CODER_PYTHON_34B_Q5)),
  LLAMA_3(
      "Llama 3",
      "Llama 3 is a family of large language models (LLMs), a collection of pretrained and "
          + "instruction tuned generative text models in 8 and 70B sizes. The Llama 3 instruction "
          + "tuned models are optimized for dialogue use cases and outperform many of the available"
          + " open source chat models on common industry benchmarks. Further, in developing these "
          + "models, we took great care to optimize helpfulness and safety.",
      PromptTemplate.LLAMA_3,
      List.of(
          HuggingFaceModel.LLAMA_3_8B_IQ3_M,
          HuggingFaceModel.LLAMA_3_8B_Q4_K_M,
          HuggingFaceModel.LLAMA_3_8B_Q5_K_M,
          HuggingFaceModel.LLAMA_3_8B_Q6_K,
          HuggingFaceModel.LLAMA_3_8B_Q8_0,
          HuggingFaceModel.LLAMA_3_70B_IQ1,
          HuggingFaceModel.LLAMA_3_70B_IQ2_XS,
          HuggingFaceModel.LLAMA_3_70B_Q4_K_M)),
  PHI_3(
      "Phi-3 Mini",
      "Phi-3 Mini is a 3.8B parameters, lightweight, state-of-the-art open model. "
          + "When assessed against benchmarks testing common sense, language understanding, math, "
          + "code, long context and logical reasoning, Phi-3 Mini-4K-Instruct showcased a robust "
          + "and state-of-the-art performance among models with less than 13 billion parameters.",
      PromptTemplate.PHI_3,
      List.of(
          HuggingFaceModel.PHI_3_3_8B_4K_IQ4_NL,
          HuggingFaceModel.PHI_3_3_8B_4K_Q5_K_M,
          HuggingFaceModel.PHI_3_3_8B_4K_Q6_K,
          HuggingFaceModel.PHI_3_3_8B_4K_Q8_0,
          HuggingFaceModel.PHI_3_3_8B_4K_FP16)),
  CODE_GEMMA(
      "CodeGemma Instruct",
      "CodeGemma Instruct is the first in a series of coding models released by Google. "
          + "As an instruct model, it specializes in being asked coding related questions, but can "
          + "also function as an autocomplete/fill-in-middle model for tools like co-pilot.\n"
          + "This model is perfect for general coding questions or code generation.",
      PromptTemplate.CODE_GEMMA,
      InfillPromptTemplate.CODE_GEMMA,
      List.of(
          HuggingFaceModel.CODE_GEMMA_7B_Q3_K_L,
          HuggingFaceModel.CODE_GEMMA_7B_Q4_K_M,
          HuggingFaceModel.CODE_GEMMA_7B_Q5_K_M,
          HuggingFaceModel.CODE_GEMMA_7B_Q6_K,
          HuggingFaceModel.CODE_GEMMA_7B_Q8_0)),
  CODE_QWEN(
      "CodeQwen1.5", """
          A specialized codeLLM built upon the Qwen1.5 language model. \
          CodeQwen1.5-7B has been pretrained with around 3 trillion tokens of code-related data. \
          It supports an extensive repertoire of 92 programming languages, and it exhibits \
          exceptional capacity in long-context understanding and generation with the ability to \
          process information of 64K tokens. In terms of performance, CodeQwen1.5 demonstrates \
          impressive capabilities in basic code generation, long-context modelling, code editing \
          and SQL. We believe this model can significantly enhance developer productivity and \
          streamline software development workflows within diverse technological environments.""",
      PromptTemplate.CODE_QWEN,
      InfillPromptTemplate.CODE_QWEN,
      List.of(
          HuggingFaceModel.CODE_QWEN_1_5_7B_Q3_K_M,
          HuggingFaceModel.CODE_QWEN_1_5_7B_Q4_K_M,
          HuggingFaceModel.CODE_QWEN_1_5_7B_Q5_K_M,
          HuggingFaceModel.CODE_QWEN_1_5_7B_Q6_K)),
  STABLE_CODE(
      "Stable Code Instruct", """
      stable-code-instruct-3b is a 2.7B billion parameter decoder-only language model tuned from \
      stable-code-3b. This model was trained on a mix of publicly available datasets, synthetic \
      datasets using Direct Preference Optimization (DPO).
      This instruct tune demonstrates state-of-the-art performance (compared to models of similar \
      size) on the MultiPL-E metrics across multiple programming languages tested using BigCode's \
      Evaluation Harness, and on the code portions of MT Bench. The model is fine tuned to make it \
      usable in tasks like general purpose Code/Software Engineering like conversations and \
      SQL related generation and conversation.""",
      PromptTemplate.STABLE_CODE,
      InfillPromptTemplate.CODE_QWEN,
      List.of(
          HuggingFaceModel.STABLE_CODE_3B_Q3_K_M,
          HuggingFaceModel.STABLE_CODE_3B_Q4_K_M,
          HuggingFaceModel.STABLE_CODE_3B_Q5_K_M,
          HuggingFaceModel.STABLE_CODE_3B_Q6_K,
          HuggingFaceModel.STABLE_CODE_3B_Q8_0)),
  ;

  private final String label;
  private final String description;
  private final PromptTemplate promptTemplate;
  private final InfillPromptTemplate infillPromptTemplate;
  private final List<HuggingFaceModel> huggingFaceModels;

  LlamaModel(
      String label,
      String description,
      PromptTemplate promptTemplate,
      List<HuggingFaceModel> huggingFaceModels) {
    this(label, description, promptTemplate, null, huggingFaceModels);
  }

  LlamaModel(
      String label,
      String description,
      PromptTemplate promptTemplate,
      InfillPromptTemplate infillPromptTemplate,
      List<HuggingFaceModel> huggingFaceModels) {
    this.label = label;
    this.description = description;
    this.promptTemplate = promptTemplate;
    this.infillPromptTemplate = infillPromptTemplate;
    this.huggingFaceModels = huggingFaceModels;
  }

  public static @NotNull LlamaModel findByHuggingFaceModel(HuggingFaceModel huggingFaceModel) {
    return Arrays.stream(LlamaModel.values())
            .filter(model -> model.getHuggingFaceModels().contains(huggingFaceModel))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Unable to find correct LLM"));
  }

  public @NotNull List<HuggingFaceModel> filterSelectedModelsBySize(ModelSize selectedModelSize) {
    return selectedModelSize != null ? getHuggingFaceModels().stream()
            .filter(model -> selectedModelSize.size() == model.getParameterSize())
            .toList() : List.of();
  }

  public boolean anyDownloaded() {
    return huggingFaceModels.stream().anyMatch(HuggingFaceModel::isDownloaded);
  }

  public String getDownloadedMarker() {
    return getDownloadedMarker(anyDownloaded());
  }

  public static String getDownloadedMarker(boolean downloaded) {
    return downloaded ? "✓" : "\u2001";
  }

  public static @NotNull Path getLlamaModelsPath() {
    return Paths.get(System.getProperty("user.home"), ".codegpt/models/gguf");
  }

  @Override
  public String toString() {
    return String.join(" ", getDownloadedMarker(), label, getFormattedModelSizeRange());
  }

  /**
   * Server started: {@code CodeLlama 7B 4-bit}.
   */
  public @NotNull String toString(@NotNull HuggingFaceModel hfm) {
    return "%s %dB %d-bit".formatted(label, hfm.getParameterSize(), hfm.getQuantization());
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }

  public PromptTemplate getPromptTemplate() {
    return promptTemplate;
  }

  public InfillPromptTemplate getInfillPromptTemplate() {
    return infillPromptTemplate;
  }

  public List<HuggingFaceModel> getHuggingFaceModels() {
    return huggingFaceModels;
  }

  /**
   * Downloaded model with the biggest parameter size, otherwise first.
   */
  public HuggingFaceModel getLastExistingModelOrFirst() {
    return huggingFaceModels.stream()
            .filter(HuggingFaceModel::isDownloaded)
            .max(Comparator.comparing(HuggingFaceModel::getParameterSize))
            .orElse(huggingFaceModels.get(0));
  }

  public String getFormattedModelSizeRange() {
    var parameters = huggingFaceModels.stream()
        .map(HuggingFaceModel::getParameterSize)
        .collect(toSet());
    if (parameters.size() == 1) {
      return parameters.iterator().next() + "B";
    }
    return format("(%dB - %dB)", Collections.min(parameters), Collections.max(parameters));
  }

  public List<ModelSize> getSortedUniqueModelSizes() {
    return huggingFaceModels.stream()
            .map(hfm -> new ModelSize(hfm.getParameterSize(), hfm.isDownloaded()))
            .sorted()
            .collect(LinkedHashSet::new, ModelSize.skipSameSize(), Set::addAll)
            .stream().toList();
  }

  public static List<LlamaModel> getSorted() {
    return Arrays.stream(values()).sorted(Comparator.comparing(Enum::name)).toList();
  }

  public record ModelSize(int size, boolean downloaded) implements Comparable<ModelSize> {
    // Sort by size, but downloaded comes first: [  7B, ✓ 13B,   13B,   34B]
    private static final Comparator<ModelSize> sizeDownloadedFirst = Comparator
            .comparing(ModelSize::size)
            .thenComparing(Comparator.comparing(ModelSize::downloaded).reversed());

    @Override
    public int compareTo(@NotNull ModelSize other) {
      return sizeDownloadedFirst.compare(this, other);
    }

    private static @NotNull BiConsumer<Set<ModelSize>, ModelSize> skipSameSize() {
      return (s, e) -> {
        if (s.stream().noneMatch(v -> v.size == e.size)) {
          s.add(e);
        }
      };
    }

    @Override
    public String toString() {
      return "%s %dB".formatted(getDownloadedMarker(downloaded), size);
    }

  }
}
