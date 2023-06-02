package com.example.callapitask.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RickMortyServiceTest {

    private RickMortyService rmService = new RickMortyService();

    @Test
    void getRandomCharacter_test() {

        this.rmService.getRandomCharacter();

    }
}