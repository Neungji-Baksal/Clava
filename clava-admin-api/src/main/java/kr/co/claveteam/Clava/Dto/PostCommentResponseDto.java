package kr.co.claveteam.Clava.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@CrossOrigin
@Data
@Builder
public class PostCommentResponseDto {

    private Long id;

    private Long postId;

    private String author;

    private String description;

    private ZonedDateTime write_Date;

}
