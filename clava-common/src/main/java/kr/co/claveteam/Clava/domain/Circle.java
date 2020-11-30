package kr.co.claveteam.Clava.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Circle {

    @Id
    @GeneratedValue
    private Long id;

    private String leader;

    @NotEmpty
    private String name;

    @NotEmpty
    private String organization;

    @NotEmpty
    private String description;

    @NotEmpty
    private String category;

    @NotEmpty
    private String place;

    private String circleProfilePhoto;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CirclePosts> circlePosts;

    public void setPosts(List<CirclePosts> circlePosts) {
        this.circlePosts =
                new ArrayList<>(circlePosts);
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> circleMember;

    public void setMember(List<User> circleMember) {
        this.circleMember =
                new ArrayList<>(circleMember);
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> circleFollower;

    public void setFollower(List<User> circleFollower) {
        this.circleFollower =
                new ArrayList<>(circleFollower);
    }

}
