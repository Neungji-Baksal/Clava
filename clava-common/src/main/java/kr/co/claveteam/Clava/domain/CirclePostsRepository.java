package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CirclePostsRepository extends CrudRepository<CirclePosts, Long> {

    List <CirclePosts> findAll();

    CirclePosts save(CirclePosts circlePosts);

    List <CirclePosts> findAllByCircleId(Long circleId);

}
