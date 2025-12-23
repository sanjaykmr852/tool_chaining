package org.example;

import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Main Spring Boot Application and REST Controller for the LLM Chat endpoint.
 *
 * NOTE: You must have Ollama running locally and the 'llama3.1' model pulled 
 * for the tool chaining to function correctly.
 * * =========================================================================
 * Required file: src/main/resources/application.properties
 * =========================================================================
 * spring.ai.model.chat=ollama
 * spring.ai.ollama.chat.options.model=llama3.1 
 * spring.ai.ollama.chat.options.temperature=0.0 
 * # Optional: Configure the base URL if Ollama is running elsewhere
 * # spring.ai.ollama.base-url=http://localhost:11434 
 * =========================================================================
 */
@SpringBootApplication
public class AiToolChainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiToolChainingApplication.class, args);
    }


    @Bean
    public ApplicationRunner printToolDefinitions(List<ToolDefinition> tools) {
        return args -> {
            System.out.println("\n======= TOOL DEFINITIONS =======");
            for (ToolDefinition tool : tools) {
                System.out.println("Tool Name: " + tool.name());
                System.out.println("Description: " + tool.description());
                System.out.println("Input Schema: " + tool.inputSchema());
                System.out.println("================================");
            }
        };
    }

}