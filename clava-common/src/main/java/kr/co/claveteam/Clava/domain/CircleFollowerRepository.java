package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CircleFollowerRepository extends CrudRepository<CircleFollower, Long> {

    List<CircleFollower> findAll();

    CircleFollower save(CircleFollower circleFollower);

    List <CircleFollower> findAllByCircleId(Long circleId);

    List <CircleFollower> findAllByUserId(Long userId);
}
