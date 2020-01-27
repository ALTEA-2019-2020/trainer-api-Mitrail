package com.miage.altea.trainer_api.bo;

import java.util.List;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trainer {
    @Id
    private String name;

    @ElementCollection
    private List<Pokemon> team;
}
