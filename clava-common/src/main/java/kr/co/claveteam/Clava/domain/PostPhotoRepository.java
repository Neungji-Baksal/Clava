package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostPhotoRepository extends CrudRepository<PostPhoto, Long> {

    List<PostPhoto> findAll();

    PostPhoto save(PostPhoto postPhoto);

    List<PostPhoto> findByPostId(Long postId);
}
