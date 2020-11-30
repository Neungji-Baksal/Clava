package kr.co.claveteam.Clava.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CircleFollower {

    @Id
    @GeneratedValue
    private Long id;

    private Long circleId;

    @NotNull
    private Long userId;

}
