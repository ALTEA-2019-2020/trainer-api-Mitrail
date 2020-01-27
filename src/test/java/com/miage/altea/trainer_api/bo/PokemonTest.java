package com.miage.altea.trainer_api.bo;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.Embeddable;

class PokemonTest {

    @Test
    void pokemon_shouldBeAnEmbeddable(){
        assertNotNull(Pokemon.class.getAnnotation(Embeddable.class));
    }

}