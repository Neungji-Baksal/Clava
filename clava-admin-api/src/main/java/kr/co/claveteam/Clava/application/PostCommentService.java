package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.CirclePosts;
import kr.co.claveteam.Clava.domain.CirclePostsRepository;
import kr.co.claveteam.Clava.domain.PostComment;
import kr.co.claveteam.Clava.domain.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@CrossOrigin
public class PostCommentService {

    private PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment addComment(Long postId, String author, String description) {

        ZonedDateTime timeTemp = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        PostComment postComment = PostComment.builder()
                .postId(postId)
                .author(author)
                .description(description)
                .write_Date(timeTemp)
                .build();

        return postCommentRepository.save(postComment);
    }
}
