package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.application.PostLikeService;
import kr.co.claveteam.Clava.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@CrossOrigin
@RestController
public class PostLikeController {

    @Autowired
    private PostLikeService postLikeService;

    @Autowired
    private PostLikeRepository postLikeRepository;


    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("postId") Long postId
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);

        PostLike postLike = postLikeService.addLike(userId, postId);

        String url = "/circles/" + postId + "/like/" + postLike.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
