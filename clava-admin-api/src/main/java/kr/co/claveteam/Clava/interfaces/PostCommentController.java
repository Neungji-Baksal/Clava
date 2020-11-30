package kr.co.claveteam.Clava.interfaces;


import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.PostCommentResponseDto;
import kr.co.claveteam.Clava.application.PostCommentService;
import kr.co.claveteam.Clava.domain.CirclePosts;
import kr.co.claveteam.Clava.domain.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class PostCommentController {

    @Autowired
    private PostCommentService postCommentService;

    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<PostCommentResponseDto> create(
            Authentication authentication,
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostComment resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String author = claims.get("nickName", String.class);
        String description = resource.getDescription();

        PostComment postComment = postCommentService.addComment(
                postId, author, description);

        PostCommentResponseDto postCommentResponseDto = PostCommentResponseDto.builder()
                .id(postComment.getId())
                .postId(postComment.getPostId())
                .author(postComment.getAuthor())
                .description(postComment.getDescription())
                .write_Date(postComment.getWrite_Date())
                .build();
        
        String url = "/posts/" + postId + "/comment/" + postComment.getId();
        return ResponseEntity.created(new URI(url))
                .body(postCommentResponseDto);
    }
}
