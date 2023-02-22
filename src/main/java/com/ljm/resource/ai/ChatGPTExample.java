//package com.ljm.resource.ai;
//
///**
// * Created by jiamin5 on 2023/2/21.
// */
//
//import ai.openai.api.models.Completion;
//import ai.openai.api.requests.*;
//import ai.openai.api.responses.Engine;
//import ai.openai.api.responses.Model;
//import ai.openai.gpt.GPT;
//import ai.openai.gpt.GPTResponse;
//import ai.openai.gpt.SearchResult;
//
//import java.util.List;
//
//public class ChatGPTExample {
//
//    public static void main(String[] args) throws Exception {
//        // 设置OpenAI的API密钥
//        String apiKey = "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";
//
//        // 创建一个OpenAI GPT实例
//        GPT gpt = new GPT(apiKey);
//
//        // 查找可用的引擎
//        EngineListRequest engineListRequest = new EngineListRequest();
//        List<Engine> engines = engineListRequest.setApiKey(apiKey).execute();
//        String engineId = engines.get(0).id;
//
//        // 获取引擎
//        EngineRetrieveRequest engineRetrieveRequest = new EngineRetrieveRequest(engineId);
//        Engine engine = engineRetrieveRequest.setApiKey(apiKey).execute();
//
//        // 查找可用的模型
//        ModelListRequest modelListRequest = new ModelListRequest();
//        List<Model> models = modelListRequest.setApiKey(apiKey).execute();
//        String modelId = models.get(0).id;
//
//        // 获取模型
//        ModelRetrieveRequest modelRetrieveRequest = new ModelRetrieveRequest(modelId);
//        Model model = modelRetrieveRequest.setApiKey(apiKey).execute();
//
//        // 设置模型和引擎
//        gpt.setEngine(engineId);
//        gpt.setModel(modelId);
//
//        // 发送请求并获取响应
//        CompletionRequest completionRequest = new CompletionRequest();
//        CompletionRequest.Parameters parameters = completionRequest.createParameters();
//        parameters.setPrompt("Hello, how are you?");
//        parameters.setMaxTokens(10);
//        Completion completion = completionRequest.setParameters(parameters).setApiKey(apiKey).execute();
//
//        // 解析响应
//        GPTResponse gptResponse = new GPTResponse(completion.getChoices().get(0).getText());
//        List<SearchResult> searchResults = gptResponse.getSearchResults();
//        String response = searchResults.get(0).getText();
//        System.out.println(response);
//    }
//}
