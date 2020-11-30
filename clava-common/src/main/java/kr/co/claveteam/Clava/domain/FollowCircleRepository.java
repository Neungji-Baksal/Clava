package kr.co.claveteam.Clava.domain;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowCircleRepository extends CrudRepository<FollowCircle, Long> {

    List<FollowCircle> findAll();

    FollowCircle save(FollowCircle followCircle);

    List <FollowCircle> findAllByUserNickname(String userNickname);

    Optional <FollowCircle> findByUserNickname(String userNickname);

    Optional<FollowCircle> findById(Long id);

}
