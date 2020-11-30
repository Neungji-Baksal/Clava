package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import kr.co.claveteam.Clava.parts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private FollowCircleRepository followCircleRepository;
    private MyCircleRepository myCircleRepository;
    private CircleRepository circleRepository;
    private CircleService circleService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       FollowCircleRepository followCircleRepository,
                       MyCircleRepository myCircleRepository,
                       CircleRepository circleRepository,
                       CircleService circleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.followCircleRepository = followCircleRepository;
        this.myCircleRepository = myCircleRepository;
        this.circleRepository = circleRepository;
        this.circleService = circleService;
    }

    public User getUser(String nickname) {

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NicknameNotFoundException(nickname));


        List<FollowCircle> followCircles = followCircleRepository.findAllByUserNickname(nickname);
        List<Circle> followCircleList = new ArrayList<>();
        for(FollowCircle followCircle:followCircles) {

            Optional<Circle> optCircle = circleRepository.findById(followCircle.getCircleId());
            Circle circle = circleService.getSomeThings(optCircle.get().getName());
            followCircleList.add(circle);

        }
        user.setFollowers(followCircleList);

        List<MyCircle> myCircles = myCircleRepository.findAllByUserNickname(nickname);
        List<Circle> myCircleList = new ArrayList<>();
        for(MyCircle myCircle:myCircles){

            Optional<Circle> optCircle = circleRepository.findById(myCircle.getCircleId());
            Circle circle = circleService.getSomeThings(optCircle.get().getName());
            myCircleList.add(circle);

        }
        user.setJoinCircle(myCircleList);

        return user;
    }


    public boolean Existed(String nickname){

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NicknameNotFoundException(nickname));

        if(user != null) return true;
        else return false;
    }

    public List<User> getUsers() {

        List<User> users = userRepository.findAll();
        return users;
    }

    public User getEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));
        return user;
    }

    public User addUser(String nickname, String email, String password,
                        String name, String gender, String organization, String photourl) {

        String encodedPassword = passwordEncoder.encode(password);

        Optional<User> existedEmail = userRepository.findByEmail(email);
        if(existedEmail.isPresent()){
            throw new EmailExistedException(email);
        }

        Optional<User> existedNN = userRepository.findByNickname(nickname);
        if(existedNN.isPresent()){
            throw new NickNameExistedException(nickname);
        }

        ZonedDateTime aaa= ZonedDateTime.now();
        User user = User.builder()
                .nickname(nickname)
                .email(email)
                .password(encodedPassword)
                .name(name)
                .user_gender(gender)
                .user_organization(organization)
                .profilePhoto(photourl)
                .join_Date(aaa)
                .level(0L)
                .build();

        return userRepository.save(user);

    }

    public User updateUser(String nickname, String email, String password, String name, String gender,
                           String organization, String photourl) {

        User user = userRepository.findByNickname(nickname).orElse(null);

        String encodedPassword = passwordEncoder.encode(password);

        Optional<User> existedEmail = userRepository.findByEmail(email);
        if(existedEmail.isPresent()){
            throw new EmailExistedException(email);
        }

        Optional<User> existedNN = userRepository.findByNickname(nickname);
        if(existedNN.isPresent()){
            throw new NickNameExistedException(nickname);
        }

        user.setNickname(nickname);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setUser_gender(gender);
        user.setUser_organization(organization);
        user.setProfilePhoto(photourl);

        return user;
    }

    public User authenticate(String email, String password){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }

    public User updateImg(String nickname, String photoUrl) {

        User user = userRepository.findByNickname(nickname).orElse(null);

        user.setNickname(nickname);
        user.setProfilePhoto(photoUrl);

        return user;
    }

}
