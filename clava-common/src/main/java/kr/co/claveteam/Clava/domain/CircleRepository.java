package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CircleRepository extends CrudRepository<Circle, Long> {

    List<Circle> findAll();

    Optional<Circle> findById(Long id);

    Optional<Circle> findByName(String name);


}
