package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends CrudRepository<PostLike, Long> {

    List<PostLike> findAll();

    PostLike save(PostLike postLike);

    List<PostLike> findAllByPostId(Long postId);

    Optional<PostLike> findByPostId(Long postId);

}
