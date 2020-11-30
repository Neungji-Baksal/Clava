package kr.co.claveteam.Clava.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@CrossOrigin
@Builder
public class joinCircleDto {

    private String circleName;
}
