package roman.yandexGptService.entity.chat;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

public class Result {
    @JSONField(name = "alternatives")
    private List<ResultAlternatives> alternatives;
    @JSONField(name = "usage")
    private ResultUsage usage;
    @JSONField(name = "modelVersion")
    private String modelVersion;


    public List<ResultAlternatives> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<ResultAlternatives> alternatives) {
        this.alternatives = alternatives;
    }

    public ResultUsage getUsage() {
        return usage;
    }

    public void setUsage(ResultUsage usage) {
        this.usage = usage;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }
}