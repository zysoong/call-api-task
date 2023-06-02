package com.example.callapitask.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry){
        registry.add("rickandmorty.url", ()->mockWebServer.url("/").toString());
    }


    @Test
    @DirtiesContext
    void getRandomCharacter_whenCharacterExists_thenReturnCharacterAsResponseBody() throws Exception {

        mockWebServer.enqueue(

                new MockResponse()
                    .setHeader("Content-Type", "application/json")
                    .setBody(
                    """
                                {
                                            "info": {
                                                "count": 826,
                                                "pages": 42,
                                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                                "prev": null
                                            },
                                            "results": [
                                                {
                                                    "id": 1,
                                                    "name": "Rick Sanchez",
                                                    "status": "Alive",
                                                    "species": "Human",
                                                    "type": "",
                                                    "gender": "Male"
                                                }
                                            ]
                                }
                            """)

        );

        mockWebServer.enqueue(
                new MockResponse()
                        .setHeader("Content-Type", "application/json")
                        .setBody(
                                """
                                    {
                                        "id": 1,
                                        "name": "Rick Sanchez"
                                    }
                                """)

        );


        mockMvc.perform(MockMvcRequestBuilders.get("/api/character/random"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void getAllCharacters() throws Exception {

        mockWebServer.enqueue(

                new MockResponse()
                        .setHeader("Content-Type", "application/json")
                        .setBody(
                                """
                                                {
                                                            "info": {
                                                                "count": 826,
                                                                "pages": 42,
                                                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                                                "prev": null
                                                            },
                                                            "results": [
                                                                {
                                                                    "id": 1,
                                                                    "name": "Rick Sanchez",
                                                                    "status": "Alive",
                                                                    "species": "Human",
                                                                    "type": "",
                                                                    "gender": "Male"
                                                                }, 
                                                                {
                                                                    "id": 2,
                                                                    "name": "Morty Smith",
                                                                    "status": "Alive",
                                                                    "species": "Human",
                                                                    "type": "",
                                                                    "gender": "Male"
                                                                }
                                                            ]
                                                }
                                        """)

        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/character"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists());


    }
}