package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.Dto.joinCircleDto;
import kr.co.claveteam.Clava.domain.FollowCircle;
import kr.co.claveteam.Clava.domain.MyCircle;
import kr.co.claveteam.Clava.domain.MyCircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class MyCircleService {

    private MyCircleRepository myCircleRepository;

    @Autowired
    public MyCircleService(MyCircleRepository myCircleRepository) {
        this.myCircleRepository = myCircleRepository;
    }

    public MyCircle addJoin(String nickname,Long circleId) {
        MyCircle myCircle = new MyCircle();
        myCircle.setCircleId(circleId);
        myCircle.setUserNickname(nickname);
        return myCircleRepository.save(myCircle);
    }
}
