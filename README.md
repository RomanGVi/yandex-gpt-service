
# Yandex GPT java

SDK Yandex GPT. На данный момент работает только отправка запросов промт в синхронном режиме.

# Usage 

```java
        YandexGptService gpt = YandexGptService.builder()
                .apiKey("<ваш ключ>")
                .folderId("<идентификатор каталога>")
                .build();
        
        Message msg1 = Message.ofSystem("Ты представитель компании Яндекс"); // промт
        Message msg2 = Message.of("Чем отличается YandexGPT Pro от YandexGPT Lite?"); // сообщение от пользователя
        List<Message> messages = Arrays.asList(msg1, msg2);

        Completion completion = Completion.builder()
                .maxTokens(1000)
                .messages(messages)
                .temperature(0.8)
                .model(Model.YANDEXGPT_PRO)
                .build();
        
        CompletionResponse response = gpt.chatCompletion(completion);
        
        String version = response.getModelVersion();
        int inputTokens = response.getUsage().getInputTextTokens();
        int outputTokens = response.getUsage().getCompletionTokens();
        String status = response.getAlternatives().get(0).getStatus();
        String answerOfGpt = response.getAlternatives().get(0).getMessage().getContent();
        System.out.println(answerOfGpt);
```
