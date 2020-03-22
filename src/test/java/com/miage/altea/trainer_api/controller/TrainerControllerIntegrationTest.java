package com.miage.altea.trainer_api.controller;

import com.miage.altea.trainer_api.bo.Pokemon;
import com.miage.altea.trainer_api.bo.Trainer;
import com.miage.altea.trainer_api.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Autowired
    private TrainerRepository repository;

    @Value("spring.security.user.name")
    private String username;

    @Value("spring.security.user.password")
    private String password;


    @BeforeEach
    private void init() {
        var ash = Trainer.builder().name("Ash").build();
        var pikachu = new Pokemon(25, 18);
        ash.setTeam(List.of(pikachu));

        var misty = Trainer.builder().name("Misty").build();
        var staryu = new Pokemon(120, 18);
        var starmie = new Pokemon(121, 21);
        misty.setTeam(List.of(staryu, starmie));
        // save a couple of trainers
        repository.deleteAll();
        System.out.println("Saved " + repository.save(ash).getName());
        System.out.println("Saved " + repository.save(misty).getName());
        var savedAsh = this.restTemplate.withBasicAuth(username, password).postForObject("http://localhost:" + port + "/trainers/", ash, Trainer.class);
        var savedMisty = this.restTemplate.withBasicAuth(username, password).postForObject("http://localhost:" + port + "/trainers/", misty, Trainer.class);
        //System.out.println(savedAsh.getName() + " saved");
        //System.out.println(savedMisty.getName() + " saved");
    }

    @Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void deleteTrainer_withNameAsh_shouldReturn1() {
        this.restTemplate.delete("http://localhost:" + port + "/trainers/Ash");
        var ash = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNull(ash);
    }
}
