package kr.co.claveteam.Clava.Dto;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Data
public class S3UploaderDto {

    private String name;

    private String photoUrl;
}
