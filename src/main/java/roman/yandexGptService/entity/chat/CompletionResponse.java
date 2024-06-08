package roman.yandexGptService.entity.chat;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

public class CompletionResponse {
    @JSONField(name = "result")
    private Result result;

    public void setResult(Result result) {
        this.result = result;
    }

    public ResultUsage getUsage() {
        return result.getUsage();
    }

    public List<ResultAlternatives> getAlternatives() {
        return result.getAlternatives();
    }

    public String getModelVersion() {
        return result.getModelVersion();
    }
}