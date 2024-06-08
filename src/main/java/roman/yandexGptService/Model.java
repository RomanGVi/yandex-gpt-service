package roman.yandexGptService;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum Model {
    YANDEXGPT_PRO("yandexgpt/latest"), // 3rd generation
    YANDEXGPT_LITE("yandexgpt-lite/latest"), // 2nd generation
    YANDEDXGPT_LITE_RC("yandexgpt-lite/rc"), // 3rd generation, release candidate
    SUMMARIZATION("summarization/latest"); // 2nd generation

    final String name;
    Model(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}