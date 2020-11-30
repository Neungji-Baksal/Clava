package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.Dto.joinCircleDto;
import kr.co.claveteam.Clava.domain.FollowCircle;
import kr.co.claveteam.Clava.domain.FollowCircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class FollowCircleService {

    private FollowCircleRepository followCircleRepository;

    @Autowired
    public FollowCircleService(FollowCircleRepository followCircleRepository) {
        this.followCircleRepository = followCircleRepository;
    }

    public FollowCircle addFollow(String nickname, Long circleId) {
        FollowCircle followCircle = new FollowCircle();
        followCircle.setCircleId(circleId);
        followCircle.setUserNickname(nickname);
        return followCircleRepository.save(followCircle);
    }
}
