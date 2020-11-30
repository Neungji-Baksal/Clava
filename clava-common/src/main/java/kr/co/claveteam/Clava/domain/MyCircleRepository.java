package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MyCircleRepository extends CrudRepository<MyCircle, Long> {

    List<MyCircle> findAll();

    MyCircle save(MyCircle myCircle);

    List <MyCircle> findAllByUserNickname(String userNickname);

    Optional<MyCircle> findById(Long id);
}
