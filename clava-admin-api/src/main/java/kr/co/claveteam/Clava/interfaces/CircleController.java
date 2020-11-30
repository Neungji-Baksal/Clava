package kr.co.claveteam.Clava.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.claveteam.Clava.Dto.CircleRequestDto;
import kr.co.claveteam.Clava.Dto.CircleRequestDto2;
import kr.co.claveteam.Clava.Dto.S3UploaderDto;
import kr.co.claveteam.Clava.application.*;
import kr.co.claveteam.Clava.domain.Circle;
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
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private UserService userService;

    // 1. circle list
    // 2. circle add
    // 3. circle update
    // 4. circle delete

    @PostMapping("/circles/all")
    public List<Circle> list(
            Authentication authentication){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));

        if(right) {
            List<Circle> circles = circleService.getCircles();
            return circles;
        }

        else return null;
    }

    @PostMapping("/circles/found")
    public Circle detail(
            Authentication authentication,
            @Valid @RequestBody CircleRequestDto resource){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));


        if(right) {
            Long id = resource.getCircleId();
            Circle circle = circleService.getCircles(id);

            return circle;
        }

        else return null;
    }

    @PostMapping("/circles/found2")
    public Circle detail(
            Authentication authentication,
            @Valid @RequestBody CircleRequestDto2 resource){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));


        if(right) {
            String name = resource.getCircleName();
            Circle circle = circleService.getCircles(name);

            return circle;
        }

        else return null;
    }


    @PostMapping("/circles")
    public ResponseEntity<?> create(
            Authentication authentication,
            @RequestBody Circle resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String leader = claims.get("nickName", String.class);
        String name = resource.getName();
        String organization = resource.getOrganization();
        String description = resource.getDescription();
        String category = resource.getCategory();
        String place = resource.getPlace();
        String photoURL = resource.getCircleProfilePhoto();

        Circle circle = circleService.addCircle(
                leader,name,organization,description,category, place, photoURL);

        String url = "/circles/" + circle.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/circles/{id}")
    public String update(
            @PathVariable("id") Long id,
            @RequestBody Circle resource
    ){

        String name = resource.getName();
        String organization = resource.getOrganization();
        String description = resource.getDescription();
        String category = resource.getCategory();
        String place = resource.getPlace();
        String photoUrl = resource.getCircleProfilePhoto();

        circleService.updateCircle(id,name,organization,description,category,place,photoUrl);
        return "{}";
    }

    @PatchMapping("/circlesImg")
    public String imgPatch(
            Authentication authentication,
            @Valid @RequestBody S3UploaderDto resource){

        Claims claims = (Claims) authentication.getPrincipal();

        Boolean right = userService.Existed(claims.get("nickName", String.class));

        if(right) {
            String circleName = resource.getName();
            String photoUrl = resource.getPhotoUrl();
            circleService.updateImg(circleName, photoUrl);
            return "{}";
        }

        else return null;
    }

}
