package roman.yandexGptService.entity.chat;

import com.alibaba.fastjson2.annotation.JSONField;
import roman.yandexGptService.Model;

import java.util.List;

public class Completion {
    @JSONField(ordinal = 1) private String modelUri;
    @JSONField(ordinal = 2) private CompletionOptions completionOptions;
    @JSONField(ordinal = 3) private List<Message> messages;
    @JSONField(serialize = false) private Model model;

    private Completion(Model model, String modelUri, CompletionOptions completionOptions, List<Message> messages) {
        this.model = model;
        this.modelUri = modelUri;
        this.completionOptions = completionOptions;
        this.messages = messages;
    }

    public static CompletionBuilder builder() {
        return new CompletionBuilder();
    }

    public String getModelUri() {
        return modelUri;
    }

    public void setModelUri(String modelUri) {
        this.modelUri = modelUri;
    }

    public CompletionOptions getCompletionOptions() {
        return completionOptions;
    }

    public void setCompletionOptions(CompletionOptions completionOptions) {
        this.completionOptions = completionOptions;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Model getModel() {
        return model;
    }

    public static class CompletionBuilder {
        private Model model;
        private boolean stream;
        private Double temperature;
        private int maxTokens;
        private List<Message> messages;
        private boolean messagesSet;

        public CompletionBuilder() {
            // default values
            this.model = Model.YANDEXGPT_LITE;
            this.stream = false;
            this.temperature = 0.3;
            this.maxTokens = 2000;
        }

        public CompletionBuilder model(Model model) {
            this.model = model;
            return this;
        }

        public CompletionBuilder stream(boolean stream) {
            this.stream = stream;
            return this;
        }

        /**
         * чем выше значение этого параметра, тем более креативными и случайными будут ответы модели.
         * Принимает значения от 0 (включительно) до 1 (включительно). Значение по умолчанию: 0.3.
         * @param temperature
         *
         */
        public CompletionBuilder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        /**
         * устанавливает ограничение на выход модели в токенах.
         * Максимальное число токенов генерации зависит от модели.
         * Подробнее см. в разделе Квоты и лимиты в Yandex Foundation Models.
         * @param maxTokens
         *
         */
        public CompletionBuilder maxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        /**
         * список сообщений, которые задают контекст для модели.
         * список не должен быть пустым!
         * @param messages
         *
         */
        public CompletionBuilder messages(List<Message> messages) {
            messagesSet = true;
            this.messages = messages;
            return this;
        }

        public Completion build() {
            if (this.messages == null || !this.messagesSet) {
                throw new ChatException("Messages is null or not set. Can't build completion");
            }
            CompletionOptions options = new CompletionOptions(stream, temperature, maxTokens);
            String modelUri = "";
            return new Completion(model, modelUri, options, messages);
        }
    }

    private static class CompletionOptions {
        @JSONField(ordinal = 1) private boolean stream;
        @JSONField(ordinal = 2) private Double temperature;
        @JSONField(ordinal = 3) private int maxTokens;

        public CompletionOptions(boolean stream, Double temperature, int maxTokens) {
            this.stream = stream;
            this.temperature = temperature;
            this.maxTokens = maxTokens;
        }

        public boolean isStream() {
            return stream;
        }

        public void setStream(boolean stream) {
            this.stream = stream;
        }

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        public int getMaxTokens() {
            return maxTokens;
        }

        public void setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
        }
    }


}
