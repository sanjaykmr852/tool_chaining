package org.example.web;

import org.example.tools.ToolService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes the main Chat endpoint.
 */
@RestController
public class ChatController {

    private final ChatClient chatClient;

    private final ToolService toolService;

    /**
     * The ChatClient is injected and will automatically discover the
     * 'ToolService' bean and register its @Tool annotated methods.
     * * @param builder The ChatClient.Builder is used to create a ChatClient
     * that has access to all registered Tool beans.
     *
     * @param toolService The tool service containing the methods.
     */
    public ChatController(ChatClient.Builder builder, ToolService toolService) {
        this.chatClient = builder.defaultTools(toolService)
                .build();
        this.toolService=toolService;
    }

    /**
     * Endpoint to interact with the LLM.
     * * Test with:
     * * http://localhost:8080/chat?message=What%20is%20the%20status%20of%20order%20O6002?
     * http://localhost:8080/chat?message=Tell%20me%20the%20name%20and%20status%20for%20user%20U101.
     * http://localhost:8080/chat?message=What%20is%20the%20total%20of%20order%20O5001%20and%20what%20is%20user%20U202's%20name?
     * * @param message The user's query.
     *
     * @return The LLM's response, potentially generated after executing a tool.
     */
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "Tell me about the tools you have access to.") String message) {

        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        // Send the user prompt to the ChatClient
        String response = chatClient.prompt()
                .user(message)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .call()
                .content();

        return response;
    }
}