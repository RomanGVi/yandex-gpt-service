package roman.yandexGptService.entity.chat;

public class ResultUsage {
    private int inputTextTokens;
    private int completionTokens;
    private int totalTokens;

    @Override
    public String toString() {
        return "ResultUsage{" +
                "inputTextTokens=" + inputTextTokens +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                '}';
    }

    public int getInputTextTokens() {
        return inputTextTokens;
    }

    public void setInputTextTokens(int inputTextTokens) {
        this.inputTextTokens = inputTextTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
}
