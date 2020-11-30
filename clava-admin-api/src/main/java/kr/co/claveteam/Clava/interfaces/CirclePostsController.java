package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.CirclePostsDto;
import kr.co.claveteam.Clava.Dto.CircleRequestDto2;
import kr.co.claveteam.Clava.Dto.FoundCirclePostDto;
import kr.co.claveteam.Clava.application.CirclePostsService;
import kr.co.claveteam.Clava.application.CircleService;
import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.domain.*;
import kr.co.claveteam.Clava.parts.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class CirclePostsController {

    @Autowired
    private CirclePostsService circlePostsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CirclePostsRepository circlePostsRepository;

    @Autowired
    private PostPhotoRepository postPhotoRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private  PostLikeRepository postLikeRepository;

    @GetMapping("/posts")
    public List<CirclePosts> list(){
        List<CirclePosts> posts = circlePostsService.getPosts();
        return posts;
    }

    @PostMapping("/posts/found/{postId}")
    public CirclePosts detail(
            Authentication authentication,
            @PathVariable("postId") Long postId
            ){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));


        if(right) {

            CirclePosts circlePost = circlePostsRepository.findById(postId).
                    orElseThrow( () -> new PostNotFoundException(postId));

            List<PostComment> optPosts = postCommentRepository.findByPostId(circlePost.getId());
            circlePost.setComment(optPosts);

            List<PostPhoto> optPhotos = postPhotoRepository.findByPostId(circlePost.getId());
            circlePost.setPhoto(optPhotos);

            List<PostLike> optLikes = postLikeRepository.findAllByPostId(circlePost.getId());
            circlePost.setLikeNum(optLikes.size());
            circlePost.setLike(optLikes);

            return circlePost;
        }

        return null;
    }

    @PostMapping("/circles/{circleId}/posts")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("circleId") Long circleId,
            @Valid @RequestBody CirclePostsDto resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String author = claims.get("nickName", String.class);
        String description = resource.getDescription();
        ArrayList<String> temp = resource.getPhotoUrl();
        List<String> photoUrl = new ArrayList<>();
        if(temp != null){
            for(String str:temp) photoUrl.add(str);
        }

        if(temp == null){ }

        CirclePosts circlePosts = circlePostsService.addPost(
                circleId, author, description, photoUrl);

        String url = "/circles/" + circleId + "/posts/" + circlePosts.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @PatchMapping("/posts/{postId}")
    public String update(
            Authentication authentication,
            @PathVariable("postId") String postId,
            @RequestBody CirclePostsDto resource
    ){

        Claims claims = (Claims) authentication.getPrincipal();

        String author = claims.get("nickName", String.class);
        String description = resource.getDescription();
        ArrayList<String> temp = resource.getPhotoUrl();
        List<String> photoUrl = new ArrayList<>();
        if(temp != null){
            for(String str:temp) photoUrl.add(str);
        }

        if(temp == null){ }

        return "{}";
    }

}
