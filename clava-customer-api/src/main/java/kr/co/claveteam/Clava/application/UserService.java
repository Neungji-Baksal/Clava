package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getEmail(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public User registerUser(String nickname, String email, String password,
                             String name, String gender, String organization, String photourl) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        Optional<User> existedEmail = userRepository.findByEmail(email);
        if(existedEmail.isPresent()){
            throw new EmailExistedException(email);
        }

        Optional<User> existedNN = userRepository.findByNickname(nickname);
        if(existedNN.isPresent()){
            throw new NickNameExistedException(nickname);
        }

        User user = User.builder()
                .nickname(nickname)
                .email(email)
                .password(encodedPassword)
                .name(name)
                .user_gender(gender)
                .user_organization(organization)
                .profilePhoto(photourl)
                .join_Date(ZonedDateTime.now())
                .level(1L)
                .build();

        return userRepository.save(user);
    }
}

