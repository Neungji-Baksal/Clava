package kr.co.claveteam.Clava.Dto;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Data
public class UserRequestDto {

    private String nickName;
}
