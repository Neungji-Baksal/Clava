package kr.co.claveteam.Clava.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long postId;

    private String author;

    @NotEmpty
    @Column(length = 1000)
    private String description;

    private ZonedDateTime write_Date;

}
