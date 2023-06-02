package com.example.callapitask.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RickMortyService {

    private final WebClient webClient = WebClient.create("https://rickandmortyapi.com/api/");

}
