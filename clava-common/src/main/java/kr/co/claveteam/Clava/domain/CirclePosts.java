package kr.co.claveteam.Clava.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mysql.cj.x.protobuf.MysqlxExpect;
import lombok.*;
import org.hibernate.validator.internal.engine.valueextraction.ValueExtractorDescriptor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CirclePosts{

    @Id
    @GeneratedValue
    private Long id;

    private Long circleId;

    private String author;

    @NotEmpty
    @Column(length = 3000)
    private String description;

    private Integer likeNum;

    private ZonedDateTime write_Date;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostComment> postComment;

    public void setComment(List<PostComment> postComment) {
        this.postComment =
                new ArrayList<>(postComment);
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostPhoto> postPhoto;

    public void setPhoto(List<PostPhoto> postPhoto) {
        this.postPhoto =
                new ArrayList<>(postPhoto);
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostLike> postLike;

    public void setLike(List<PostLike> postLike) {
        this.postLike =
                new ArrayList<>(postLike);
    }

}
