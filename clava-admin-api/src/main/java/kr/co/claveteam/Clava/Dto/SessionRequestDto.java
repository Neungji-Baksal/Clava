package kr.co.claveteam.Clava.Dto;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Data
public class SessionRequestDto {

    private String email;

    private String password;

}
