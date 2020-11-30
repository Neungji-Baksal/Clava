package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.CirclePostsDto;
import kr.co.claveteam.Clava.Dto.DeleteRequestDto;
import kr.co.claveteam.Clava.application.DeleteService;
import kr.co.claveteam.Clava.domain.CirclePosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DeleteController {

    @Autowired
    private DeleteService deleteService;

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(
            Authentication authentication
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        Long uid = claims.get("userId", Long.class);
        deleteService.userDeactivated(uid);

        String url = "/delete/user";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @DeleteMapping("/delete/follower")
    public ResponseEntity<?> deleteFollower(
            Authentication authentication,
            @Valid @RequestBody DeleteRequestDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        String nickname = claims.get("nickName", String.class);
        Long uid = claims.get("userId", Long.class);
        Long deleteId= resource.getDeleteId(); // 삭제할 동아리 id
        deleteService.unFollow(nickname, uid, deleteId);

        String url = "/delete/follower";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @DeleteMapping("/delete/myCircle")
    public ResponseEntity<?> deleteMyCircle(
            Authentication authentication,
            @Valid @RequestBody DeleteRequestDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        String nickname  = claims.get("nickName", String.class);
        Long uid = claims.get("userId", Long.class);
        Long deleteId= resource.getDeleteId(); // 삭제할 동아리 id
        deleteService.unMember(nickname, uid, deleteId);

        String url = "/delete/myCircle";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @DeleteMapping("/delete/post")
    public ResponseEntity<?> deletePost(
            Authentication authentication,
            @Valid @RequestBody DeleteRequestDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        Long uid = claims.get("userId", Long.class);
        String nickname  = claims.get("nickName", String.class);
        Long deleteId = resource.getDeleteId();
        deleteService.deletePost(nickname, uid , deleteId);

        String url = "/delete/post";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @DeleteMapping("/delete/comment")
    public ResponseEntity<?> deleteComment(
            Authentication authentication,
            @Valid @RequestBody DeleteRequestDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        Long deleteId = resource.getDeleteId();
        deleteService.deleteComment(deleteId);

        String url = "/delete/comment";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @DeleteMapping("/delete/like")
    public ResponseEntity<?> deleteLike(
            Authentication authentication,
            @Valid @RequestBody DeleteRequestDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        Long postId = resource.getDeleteId();
        Long userId = claims.get("userId", Long.class);
        deleteService.deleteLike(postId, userId);

        String url = "/delete/like";
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
