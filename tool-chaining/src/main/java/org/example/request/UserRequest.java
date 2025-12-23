package org.example.request;

import org.springframework.ai.tool.annotation.ToolParam;

public record UserRequest(@ToolParam String userId) {}