package kr.co.claveteam.Clava.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<kr.co.claveteam.Clava.domain.User, Long> {

    List<kr.co.claveteam.Clava.domain.User> findAll();

    Optional<kr.co.claveteam.Clava.domain.User> findById(Long id);

    Optional<kr.co.claveteam.Clava.domain.User> findByNickname(String nickname);

    Optional<kr.co.claveteam.Clava.domain.User> findByEmail(String email);

}
