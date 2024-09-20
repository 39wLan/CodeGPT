package ee.carlrobert.codegpt.client.Zhengyan.completion.dto.zy;

public class ExtModel {

    private int max_tokens;
    private double temperature;
    private boolean stream;

    // Getters and Setters
    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    // Additional methods like toString(), equals(), hashCode() can be added here.
}

