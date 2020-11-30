package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@CrossOrigin
public class CircleFollowerService {

    private CircleFollowerRepository circleFollowerRepository;
    private UserRepository userRepository;

    @Autowired
    public CircleFollowerService(CircleFollowerRepository circleFollowerRepository,
                                 UserRepository userRepository) {
        this.circleFollowerRepository = circleFollowerRepository;
        this.userRepository = userRepository;
    }

    public CircleFollower addFollower(Long circleId, String userNickname) {

        CircleFollower circleFollower = new CircleFollower();

        Optional<User> mockUser = userRepository.findByNickname(userNickname);
        User temp = mockUser.get();
        Long userId = temp.getId();

        circleFollower.setCircleId(circleId);
        circleFollower.setUserId(userId);
        return circleFollowerRepository.save(circleFollower);
    }
}
