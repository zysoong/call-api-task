package com.example.callapitask.service;

import com.example.callapitask.model.OverviewCharacters;
import com.example.callapitask.model.RMCharacter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class RickMortyService {

    private final WebClient webClient = WebClient.create("https://rickandmortyapi.com/api/");



    public RMCharacter getRandomCharacter(){


        ResponseEntity<OverviewCharacters> responseOverview = webClient.get()
                .uri("character")
                .retrieve()
                .toEntity(OverviewCharacters.class)
                .block();

        OverviewCharacters overview = Objects.requireNonNull(responseOverview).getBody();

        int lower = 1;
        int upper = overview.info().count();
        int random = (int) (Math.random() * (upper - lower)) + lower;

        ResponseEntity<RMCharacter> responseEntity = webClient.get()
                .uri("character/" + random)
                .retrieve()
                .toEntity(RMCharacter.class)
                .block();

        RMCharacter character = Objects.requireNonNull(responseEntity).getBody();
        return character;

    }




}
