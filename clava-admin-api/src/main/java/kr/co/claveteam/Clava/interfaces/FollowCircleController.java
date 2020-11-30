package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.joinCircleDto;
import kr.co.claveteam.Clava.application.CircleFollowerService;
import kr.co.claveteam.Clava.application.CircleService;
import kr.co.claveteam.Clava.application.FollowCircleService;
import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
public class FollowCircleController {

    @Autowired
    private FollowCircleService followCircleService;

    @Autowired
    private CircleFollowerService circleFollowerService;

    @PostMapping("/users/follower/{circleId}")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("circleId") Long circleId
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String userNickname = claims.get("nickName", String.class);

        FollowCircle followCircle = followCircleService.addFollow(
                userNickname,circleId);

        CircleFollower circleFollower = circleFollowerService.addFollower
                (circleId, userNickname);

        String url = "/users" + "/follower/" + followCircle.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
