package kr.co.claveteam.Clava.Dto;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@Data
public class CirclePostsDto {

    private String description;

    private ArrayList<String> photoUrl;
}
