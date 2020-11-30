package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.CircleMember;
import kr.co.claveteam.Clava.domain.CircleMemberRepository;
import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@CrossOrigin
public class CircleMemberService {

    private CircleMemberRepository circleMemberRepository;
    private UserRepository userRepository;

    @Autowired
    public CircleMemberService(CircleMemberRepository circleMemberRepository,
                               UserRepository userRepository) {
        this.circleMemberRepository = circleMemberRepository;
        this.userRepository = userRepository;
    }

    public CircleMember addMember(Long circleId, String userNickname) {

        CircleMember circleMember = new CircleMember();

        Optional<User> mockUser = userRepository.findByNickname(userNickname);
        User temp = mockUser.get();
        Long userId = temp.getId();

        circleMember.setCircleId(circleId);
        circleMember.setUserId(userId);
        circleMember.setLevel(1L);
        return circleMemberRepository.save(circleMember);
    }
}