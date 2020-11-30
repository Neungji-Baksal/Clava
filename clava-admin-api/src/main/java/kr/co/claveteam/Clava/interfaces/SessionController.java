package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.Dto.SessionRequestDto;
import kr.co.claveteam.Clava.Dto.SessionResponseDto;
import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {

        String url = "/auth";
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);
        String accessToken = jwtUtil.createToken(user.getId(),user.getNickname());

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder()
                .accessToken(accessToken)
                .userNickname(user.getNickname())
                .build();

        return ResponseEntity.created(new URI(url))
                .body(sessionResponseDto);
    }
}
