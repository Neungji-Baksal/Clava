package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CircleMemberRepository extends CrudRepository<CircleMember, Long> {

    List<CircleMember> findAll();

    CircleMember save(CircleMember circleMember);

    List <CircleMember> findAllByCircleId(Long circleId);

    List <CircleMember> findAllByUserId(Long userId);
}
