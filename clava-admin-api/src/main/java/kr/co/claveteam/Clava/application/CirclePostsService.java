package kr.co.claveteam.Clava.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.claveteam.Clava.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class CirclePostsService {

    private CirclePostsRepository circlePostsRepository;
    private PostPhotoRepository postPhotoRepository;

    @Autowired
    public CirclePostsService(CirclePostsRepository circlePostsRepository,
                              PostPhotoRepository postPhotoRepository) {
        this.circlePostsRepository = circlePostsRepository;
        this.postPhotoRepository = postPhotoRepository;
    }

    public List<CirclePosts> getPosts() {

        return circlePostsRepository.findAll();
    }


    public CirclePosts addPost(Long circleId, String author, String description, List<String> photoUrl) {

        //LocalDateTime dt = LocalDateTime.now();
        //ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime timeTemp = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        CirclePosts circlePosts = CirclePosts.builder()
                .circleId(circleId)
                .author(author)
                .description(description)
                .likeNum(0)
                .write_Date(timeTemp)
                .build();

        CirclePosts mockPosts = circlePostsRepository.save(circlePosts);

        if (photoUrl != null) {
            List<PostPhoto> postPhotoList = new ArrayList<>();
            for (String str : photoUrl) {
                PostPhoto temp = new PostPhoto();
                PostPhoto postPhoto = addPostImg(mockPosts.getId(), str, temp);
                postPhotoList.add(postPhoto);
            }
            circlePosts.setPhoto(postPhotoList);
        }

        return mockPosts;
    }

    public PostPhoto addPostImg(Long postId, String photoUrl, PostPhoto postPhoto) {
        postPhoto.setPostId(postId);
        postPhoto.setPhotoUrl(photoUrl);
        return postPhotoRepository.save(postPhoto);
    }
}
