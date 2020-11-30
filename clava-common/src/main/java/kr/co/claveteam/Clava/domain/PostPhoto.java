package kr.co.claveteam.Clava.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostPhoto{

    @Id
    @GeneratedValue
    private Long id;

    private Long postId;

    private String photoUrl;

}
