package kr.co.claveteam.Clava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String user_gender;

    @NotEmpty
    private String user_organization;

    private String profilePhoto;

    private ZonedDateTime join_Date;

    private Long level;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Circle> followCircle;

    public void setFollowers(List<Circle> followCircle) {
        this.followCircle =
                new ArrayList<>(followCircle);
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Circle> myCircle;

    public void setJoinCircle(List<Circle> myCircle) {
        this.myCircle =
                new ArrayList<>(myCircle);
    }

}
