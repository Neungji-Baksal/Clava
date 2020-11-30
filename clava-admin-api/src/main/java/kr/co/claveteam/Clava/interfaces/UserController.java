package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.S3UploaderDto;
import kr.co.claveteam.Clava.Dto.UserRequestDto;
import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    // 1. user list
    // 2. user create
    // 3. user update
    // 4. user delete

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users/found")
    public User detail(
            Authentication authentication,
            @Valid @RequestBody UserRequestDto resource){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));

        if(right) {
            String nickname = resource.getNickName();
            User user = userService.getUser(nickname);

            return user;
        }

        else return null;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {

        String nickname = resource.getNickname();
        String email = resource.getEmail();
        String password = resource.getPassword();
        String name = resource.getName();
        String gender = resource.getUser_gender();
        String organization = resource.getUser_organization();
        String profilePhoto = resource.getProfilePhoto();

        User user = userService.addUser(nickname,email,password,name,gender,organization,profilePhoto);

        String url = "/users/" + user.getNickname();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/usersImg")
    public String imgPatch(
            Authentication authentication,
            @Valid @RequestBody S3UploaderDto resource){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));

        if(right) {
            String nickname = resource.getName();
            String photoUrl = resource.getPhotoUrl();
            userService.updateImg(nickname, photoUrl);
            return "{}";
        }

        else return null;
    }

    @PatchMapping("/users/{nickname}")
    public String update(
            Authentication authentication,
            @PathVariable("nickname") String nickname,
            @RequestBody User resource
    ){

        String email = resource.getEmail();
        String password = resource.getPassword();
        String name = resource.getName();
        String gender = resource.getUser_gender();
        String organization = resource.getUser_organization();
        String profilePhoto = resource.getProfilePhoto();

        userService.updateUser(nickname, email, password,name,gender,organization,profilePhoto);
        return "{}";
    }

}
