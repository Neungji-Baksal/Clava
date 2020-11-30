package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.CircleFollowerService;
import kr.co.claveteam.Clava.application.CircleMemberService;
import kr.co.claveteam.Clava.domain.CircleFollower;
import kr.co.claveteam.Clava.domain.CircleMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class CircleFollowerController {


    @Autowired
    private CircleFollowerService circleFollowerService;

    @PostMapping("/circles/{circleId}/follower")
    public ResponseEntity<?> create(
            @PathVariable("circleId") Long circleId,
            @Valid @RequestBody CircleFollower resource
    ) throws URISyntaxException {
        //CircleFollower circleFollower = circleFollowerService.addFollower(circleId, resource);

        String url = "/circles/" + circleId + "/follower/";// + circleFollower.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }


}