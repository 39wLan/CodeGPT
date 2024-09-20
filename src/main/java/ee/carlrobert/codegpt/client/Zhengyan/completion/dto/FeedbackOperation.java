package ee.carlrobert.codegpt.client.Zhengyan.completion.dto;

public enum FeedbackOperation {
    COPY(1),
    CREATE(2),
    INSERT(3),
    REPLACE(4);

    private final int value;

    FeedbackOperation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
