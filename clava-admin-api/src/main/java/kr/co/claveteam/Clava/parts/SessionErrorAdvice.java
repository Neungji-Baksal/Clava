package kr.co.claveteam.Clava.parts;

import kr.co.claveteam.Clava.Dto.ErrorMessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class SessionErrorAdvice {

    //@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotExistedException.class)
    public ResponseEntity<ErrorMessageResponseDto> emailNotExistedExceptionHandler() throws URISyntaxException {

        String msg = "이메일이 존재하지 않습니다.";

        ErrorMessageResponseDto errorMessageResponseDto = ErrorMessageResponseDto.builder()
                .msg(msg)
                .build();

        return ResponseEntity.created(new URI("/auth"))
                .body(errorMessageResponseDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<ErrorMessageResponseDto> passwordWrongExceptionHandler() throws URISyntaxException {

        String msg = "비밀번호가 틀렸습니다.";

        ErrorMessageResponseDto errorMessageResponseDto = ErrorMessageResponseDto.builder()
                .msg(msg)
                .build();

        return ResponseEntity.created(new URI("/auth"))
                .body(errorMessageResponseDto);
    }
}