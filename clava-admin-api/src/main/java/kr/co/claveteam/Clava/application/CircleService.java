package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import kr.co.claveteam.Clava.parts.CircleNotFoundException;
import kr.co.claveteam.Clava.parts.EmailExistedException;
import kr.co.claveteam.Clava.parts.NameExistedException;
import kr.co.claveteam.Clava.parts.NickNameExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CircleService {

    private CircleRepository circleRepository;
    private CirclePostsRepository circlePostsRepository;
    private CircleMemberRepository circleMemberRepository;
    private CircleFollowerRepository circleFollowerRepository;
    private UserRepository userRepository;
    private PostCommentRepository postCommentRepository;
    private PostPhotoRepository postPhotoRepository;
    private PostLikeRepository postLikeRepository;

    @Autowired
    public CircleService(CircleRepository circleRepository,
                         CirclePostsRepository circlePostsRepository,
                         CircleMemberRepository circleMemberRepository,
                         CircleFollowerRepository circleFollowerRepository,
                         UserRepository userRepository,
                         PostCommentRepository postCommentRepository,
                         PostPhotoRepository postPhotoRepository,
                         PostLikeRepository postLikeRepository) {

        this.circleRepository = circleRepository;
        this.circlePostsRepository = circlePostsRepository;
        this.circleMemberRepository = circleMemberRepository;
        this.circleFollowerRepository = circleFollowerRepository;
        this.userRepository = userRepository;
        this.postCommentRepository = postCommentRepository;
        this.postPhotoRepository = postPhotoRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public List<Circle> getCircles() {

        List<Circle> circles = circleRepository.findAll();
        return circles;
    }

    /*public List<Circle> getCircles() {

        List<Circle> circles = circleRepository.findAll();
        List<Circle> makeCircles = new ArrayList<>();
        for(Circle temp:circles){

            Circle makeCircle = getCircles(temp.getName());
            makeCircles.add(makeCircle);
        }
        return makeCircles;
    }*/

    public Circle getCircles(Long id){

        Circle circle = circleRepository.findById(id)
                .orElseThrow(() -> new CircleNotFoundException(id));

        List<CirclePosts> circlePosts = circlePostsRepository.findAllByCircleId(id);
        circle.setPosts(circlePosts);

        for(CirclePosts circlePost:circlePosts){

            List<PostComment> optPosts = postCommentRepository.findByPostId(circlePost.getId());
            circlePost.setComment(optPosts);

            List<PostPhoto> optPhotos = postPhotoRepository.findByPostId(circlePost.getId());
            circlePost.setPhoto(optPhotos);

            List<PostLike> optLikes = postLikeRepository.findAllByPostId(circlePost.getId());
            circlePost.setLikeNum(optLikes.size());
            circlePost.setLike(optLikes);
        }

        List<CircleMember> circleMembers = circleMemberRepository.findAllByCircleId(id);
        List<User> circleMemberList = new ArrayList<>();
        for(CircleMember circleMember:circleMembers){

            Optional<User> optUser = userRepository.findById(circleMember.getUserId());
            User user = optUser.get();
            user.setLevel(circleMember.getLevel());
            circleMemberList.add(user);
        }
        circle.setMember(circleMemberList);

        List<CircleFollower> circleFollowers = circleFollowerRepository.findAllByCircleId(id);
        List<User> circleFollowerList = new ArrayList<>();
        for(CircleFollower circleFollower:circleFollowers){

            Optional<User> optUser = userRepository.findById(circleFollower.getUserId());
            User user = optUser.get();
            circleFollowerList.add(user);
        }
        circle.setFollower(circleFollowerList);

        return circle;
    }

    public Circle getCircles(String name){

        Circle circle = circleRepository.findByName(name)
                .orElseThrow(() -> new CircleNotFoundException(name));

        Long id = circle.getId();
        List<CirclePosts> circlePosts = circlePostsRepository.findAllByCircleId(id);
        circle.setPosts(circlePosts);

        for(CirclePosts circlePost:circlePosts){

            List<PostComment> optPosts = postCommentRepository.findByPostId(circlePost.getId());
            circlePost.setComment(optPosts);

            List<PostPhoto> optPhotos = postPhotoRepository.findByPostId(circlePost.getId());
            circlePost.setPhoto(optPhotos);

            List<PostLike> optLikes = postLikeRepository.findAllByPostId(circlePost.getId());
            circlePost.setLikeNum(optLikes.size());
            circlePost.setLike(optLikes);
        }

        List<CircleMember> circleMembers = circleMemberRepository.findAllByCircleId(id);
        List<User> circleMemberList = new ArrayList<>();
        for(CircleMember circleMember:circleMembers){

            Optional<User> optUser = userRepository.findById(circleMember.getUserId());
            User user = optUser.get();
            user.setLevel(circleMember.getLevel());
            circleMemberList.add(user);
        }
        circle.setMember(circleMemberList);

        List<CircleFollower> circleFollowers = circleFollowerRepository.findAllByCircleId(id);
        List<User> circleFollowerList = new ArrayList<>();
        for(CircleFollower circleFollower:circleFollowers){

            Optional<User> optUser = userRepository.findById(circleFollower.getUserId());
            User user = optUser.get();
            circleFollowerList.add(user);
        }
        circle.setFollower(circleFollowerList);

        return circle;
    }

    public Circle getSomeThings(String name){
        Circle circle = circleRepository.findByName(name)
                .orElseThrow(() -> new CircleNotFoundException(name));

        Long id = circle.getId();
        List<CirclePosts> circlePosts = circlePostsRepository.findAllByCircleId(id);
        List<CirclePosts> temp = new ArrayList<>();

        for(CirclePosts circlePost:circlePosts){

            List<PostComment> optPosts = postCommentRepository.findByPostId(circlePost.getId());
            circlePost.setComment(optPosts);

            List<PostPhoto> optPhotos = postPhotoRepository.findByPostId(circlePost.getId());
            circlePost.setPhoto(optPhotos);

            List<PostLike> optLikes = postLikeRepository.findAllByPostId(circlePost.getId());
            circlePost.setLikeNum(optLikes.size());
            circlePost.setLike(optLikes);

            temp.add(circlePost);
        }

        circle.setPosts(temp);

        return circle;
    }

    public Circle addCircle(String leader, String name, String organization, String description,
                            String category, String place, String photoURL) {

        Optional<Circle> existedName = circleRepository.findByName(name);
        if(existedName.isPresent()){
            throw new NameExistedException(name);
        }

        Circle circle = Circle.builder()
                .leader(leader)
                .name(name)
                .organization(organization)
                .description(description)
                .category(category)
                .place(place)
                .circleProfilePhoto(photoURL)
                .build();

        return circleRepository.save(circle);
    }

    public Circle updateImg(String circleName, String photoUrl) {

        Circle circle = circleRepository.findByName(circleName).orElse(null);

        circle.setName(circleName);
        circle.setCircleProfilePhoto(photoUrl);

        return circle;
    }

    public Circle updateCircle(Long id, String name, String organization, String description,
                                  String category, String place, String photoUrl) {

        Circle circle = circleRepository.findById(id).orElse(null);

        circle.setName(name);
        circle.setOrganization(organization);
        circle.setDescription(description);
        circle.setCategory(category);
        circle.setPlace(place);
        circle.setCircleProfilePhoto(photoUrl);

        return circle;
    }
}
