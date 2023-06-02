package com.example.callapitask.service;

import com.example.callapitask.model.OverviewCharacters;
import com.example.callapitask.model.RMCharacter;
import com.example.callapitask.model.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final WebClient webClient = WebClient.create("http://localhost:8081/api");

    public Student postStudent(Student studentToAdd){

        ResponseEntity<Student> responseEntity = webClient.post()
                .uri("/students")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(studentToAdd)
                .retrieve()
                .toEntity(Student.class)
                .block();

        Student addedStudent = Objects.requireNonNull(responseEntity).getBody();
        return addedStudent;

    }

}
