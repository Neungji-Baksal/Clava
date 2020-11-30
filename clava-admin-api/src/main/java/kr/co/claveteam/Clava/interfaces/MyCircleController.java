package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.joinCircleDto;
import kr.co.claveteam.Clava.application.CircleMemberService;
import kr.co.claveteam.Clava.application.DeleteService;
import kr.co.claveteam.Clava.application.FollowCircleService;
import kr.co.claveteam.Clava.application.MyCircleService;
import kr.co.claveteam.Clava.domain.CircleMember;
import kr.co.claveteam.Clava.domain.FollowCircle;
import kr.co.claveteam.Clava.domain.FollowCircleRepository;
import kr.co.claveteam.Clava.domain.MyCircle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@CrossOrigin
public class MyCircleController {

    @Autowired
    private MyCircleService myCircleService;

    @Autowired
    private CircleMemberService circleMemberService;

    @Autowired
    private FollowCircleRepository followCircleRepository;

    @Autowired
    private DeleteService deleteService;

    @PostMapping("/users/joinCircle/{circleId}")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("circleId") Long circleId
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String userNickname = claims.get("nickName", String.class);
        Long userId = claims.get("userId", Long.class);

        Optional<FollowCircle> temp = followCircleRepository.findByUserNickname(userNickname);
        if(temp.isPresent()) {
            FollowCircle followCircle = temp.get();
            if(circleId.equals(followCircle.getCircleId())) {
                String Follower = followCircle.getUserNickname();
                deleteService.unFollow(userNickname, userId, followCircle.getCircleId());
            }
        }

        MyCircle myCircle = myCircleService.addJoin(
                userNickname, circleId);
        CircleMember circleMember = circleMemberService.addMember(
                circleId, userNickname);

        String url = "/users/joinCircle/" + myCircle.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
