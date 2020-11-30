package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.CircleMemberService;
import kr.co.claveteam.Clava.domain.CircleMember;
import kr.co.claveteam.Clava.domain.CircleMemberRepository;
import kr.co.claveteam.Clava.domain.CirclePosts;
import kr.co.claveteam.Clava.domain.FollowCircle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class CircleMemberController {


    @Autowired
    private CircleMemberService circleMemberService;

    @PostMapping("/circles/{circleId}/member")
    public ResponseEntity<?> create(
            @PathVariable("circleId") Long circleId,
            @Valid @RequestBody CircleMember resource
    ) throws URISyntaxException {
        //CircleMember circleMember = circleMemberService.addMember(circleId, resource);

        String url = "/circles/" + circleId + "/member/";// + circleMember.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }


}
