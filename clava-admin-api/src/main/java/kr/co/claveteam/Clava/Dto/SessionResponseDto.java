package kr.co.claveteam.Clava.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Data
@CrossOrigin
@Builder
public class SessionResponseDto {

    private String accessToken;

    private String userNickname;

}
