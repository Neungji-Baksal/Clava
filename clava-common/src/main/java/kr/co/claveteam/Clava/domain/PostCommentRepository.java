package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {

    List<PostComment> findAll();

    PostComment save(PostComment postComment);

    List<PostComment> findByPostId(Long postId);
}
