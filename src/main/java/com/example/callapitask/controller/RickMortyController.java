package com.example.callapitask.controller;

import com.example.callapitask.model.RMCharacter;
import com.example.callapitask.service.RickMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/character")
public class RickMortyController {

    private RickMortyService rmService;

    public RickMortyController(RickMortyService rmService){
        this.rmService = rmService;
    }

    @GetMapping("random")
    public RMCharacter getCharacter(){
        return this.rmService.getRandomCharacter();
    }

    @GetMapping
    public List<RMCharacter> getAllCharacters(){
        return this.rmService.getAllCharacters();
    }

}
