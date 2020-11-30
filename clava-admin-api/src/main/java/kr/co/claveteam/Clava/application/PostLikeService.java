package kr.co.claveteam.Clava.application;


import kr.co.claveteam.Clava.domain.FollowCircleRepository;
import kr.co.claveteam.Clava.domain.PostLike;
import kr.co.claveteam.Clava.domain.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class PostLikeService {

    private PostLikeRepository postLikeRepository;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public PostLike addLike(Long userId, Long postId) {

        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
        return postLikeRepository.save(postLike);

    }
}
