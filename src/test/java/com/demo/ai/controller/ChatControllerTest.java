package com.demo.ai.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChatControllerTest {

    private MockMvc mockMvc;
    private ChatClient chatClient;
    private ChatClient.ChatClientRequestSpec requestSpec;
    private ChatClient.CallResponseSpec callResponseSpec;

    @BeforeEach
    void setUp() {
        ChatClient.Builder builder = mock(ChatClient.Builder.class);
        chatClient = mock(ChatClient.class);
        requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        callResponseSpec = mock(ChatClient.CallResponseSpec.class);

        when(builder.build()).thenReturn(chatClient);
        when(chatClient.prompt()).thenReturn(requestSpec);

        mockMvc = MockMvcBuilders.standaloneSetup(new ChatController(builder)).build();
    }

    @Test
    void chatReturnsModelContent() throws Exception {
        when(requestSpec.user("Hello")).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(callResponseSpec);
        when(callResponseSpec.content()).thenReturn("Hi there!");

        mockMvc.perform(get("/chat").param("prompt", "Hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi there!"));

        verify(chatClient).prompt();
        verify(requestSpec).user("Hello");
        verify(requestSpec).call();
        verify(callResponseSpec).content();
    }

    @Test
    void chatRequiresPromptParameter() throws Exception {
        mockMvc.perform(get("/chat"))
                .andExpect(status().isBadRequest());

        verify(chatClient, never()).prompt();
        verifyNoInteractions(requestSpec, callResponseSpec);
    }
}

