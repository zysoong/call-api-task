package com.example.callapitask.controller;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RickMortyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    // Run the mocked server before we send "requests" to it
    @BeforeAll
    private static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }


    @Test
    @DirtiesContext
    void getCharacter_whenCharacterExists_thenReturnCharacterAsResponseBody() {
    }

    @Test
    void getAllCharacters() {
    }
}