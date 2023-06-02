package com.example.callapitask.service;

import com.example.callapitask.model.OverviewCharacters;
import com.example.callapitask.model.RMCharacter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class RickMortyService {

    private final WebClient webClient;

    public RickMortyService(
            @Value("${rickandmorty.url}") String url
    )
    {
        this.webClient = WebClient.create(url);
    }

    public List<RMCharacter> getAllCharacters(){

        ResponseEntity<OverviewCharacters> responseOverview = webClient.get()
                .uri("character")
                .retrieve()
                .toEntity(OverviewCharacters.class)
                .block();

        List<RMCharacter> allCharacters = Objects.requireNonNull(responseOverview).getBody().results();

        return allCharacters;

    }

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
