package com.miage.altea.trainer_api.bo;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Pokemon {
    private int pokemonTypeId;
    private int level;
}
