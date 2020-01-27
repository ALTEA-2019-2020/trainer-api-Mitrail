package com.miage.altea.trainer_api.controller;

import com.miage.altea.trainer_api.bo.Trainer;
import com.miage.altea.trainer_api.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    Iterable<Trainer> getAllTrainers(){
        return this.trainerService.getAllTrainers();
    }

    @PostMapping("/")
    ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer) {
        return new ResponseEntity<>(this.trainerService.createTrainer(trainer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Integer> deleteTrainer(@PathVariable String name){
        return new ResponseEntity<>(this.trainerService.deleteTrainer(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name){
        return this.trainerService.getTrainer(name);
    }

}
