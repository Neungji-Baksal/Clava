package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import kr.co.claveteam.Clava.parts.NicknameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
public class DeleteService {

    private UserRepository userRepository;
    private FollowCircleRepository followCircleRepository;
    private MyCircleRepository myCircleRepository;
    private CircleRepository circleRepository;
    private CirclePostsRepository circlePostsRepository;
    private CircleFollowerRepository circleFollowerRepository;
    private CircleMemberRepository circleMemberRepository;
    private PostCommentRepository postCommentRepository;
    private PostPhotoRepository postPhotoRepository;
    private PostLikeRepository postLikeRepository;

    @Autowired
    private DeleteService(UserRepository userRepository,
                          FollowCircleRepository followCircleRepository,
                          MyCircleRepository myCircleRepository,
                          CircleRepository circleRepository,
                          CirclePostsRepository circlePostsRepository,
                          CircleFollowerRepository circleFollowerRepository,
                          CircleMemberRepository circleMemberRepository,
                          PostCommentRepository postCommentRepository,
                          PostPhotoRepository postPhotoRepository,
                          PostLikeRepository postLikeRepository){

        this.userRepository = userRepository;
        this.followCircleRepository = followCircleRepository;
        this.myCircleRepository = myCircleRepository;
        this.circleRepository = circleRepository;
        this.circlePostsRepository = circlePostsRepository;
        this.circleFollowerRepository = circleFollowerRepository;
        this.circleMemberRepository = circleMemberRepository;
        this.postCommentRepository = postCommentRepository;
        this.postPhotoRepository = postPhotoRepository;
        this.postLikeRepository = postLikeRepository;
    }


    public void userDeactivated(Long uid) {

        Optional<User> user = userRepository.findById(uid);
        User temp = user.get();
        String nickname = temp.getNickname();

        if(user != null){

            followDelete(nickname, uid);
            myCircleDelete(nickname, uid);
            userRepository.deleteById(uid);
        }

    }

    public void followDelete(String nickname, Long id){

        List<FollowCircle> followCircles = followCircleRepository.findAllByUserNickname(nickname);
        for(FollowCircle followCircle:followCircles) {

            List<CircleFollower> circleFollowers =
                    circleFollowerRepository.findAllByCircleId(id);

            for (CircleFollower circleFollower : circleFollowers) {
                if (circleFollower.getUserId().equals(id)) {
                    circleFollowerRepository.deleteById(circleFollower.getId());
                    followCircleRepository.deleteById(followCircle.getId());
                }
            }
        }
    }

    public void myCircleDelete(String nickname, Long id){

        List<MyCircle> myCircles = myCircleRepository.findAllByUserNickname(nickname);
        for(MyCircle myCircle:myCircles) {

            List<CircleMember> circleMembers =
                    circleMemberRepository.findAllByCircleId(id);

            for (CircleMember circleMember : circleMembers) {
                if (circleMember.getUserId().equals(id)) {
                    circleMemberRepository.deleteById(circleMember.getId());
                    myCircleRepository.deleteById(myCircle.getId());
                }
            }
        }
    }

    public void unFollow(String nickname, Long uid, Long tempId) {

        List<FollowCircle> followCircles = followCircleRepository.findAllByUserNickname(nickname);
        for(FollowCircle followCircle:followCircles) {

            if(tempId.equals(followCircle.getCircleId())) {

                Long deleteId = followCircle.getId();
                followCircleRepository.deleteById(deleteId);

                List<CircleFollower> circleFollowers =
                        circleFollowerRepository.findAllByUserId(uid);

                for (CircleFollower circleFollower : circleFollowers) {
                    if (circleFollower.getCircleId().equals(tempId)) {
                        circleFollowerRepository.deleteById(circleFollower.getId());
                    }
                }
            }
        }

    }

    public void unMember(String nickname, Long uid, Long tempId) {

        List<MyCircle> myCircles = myCircleRepository.findAllByUserNickname(nickname);
        for(MyCircle myCircle:myCircles) {

            Long cid = myCircle.getCircleId();
            if (tempId.equals(cid)) {
                
                Long deleteId = myCircle.getId();
                myCircleRepository.deleteById(deleteId);
                List<CircleMember> circleMembers =
                        circleMemberRepository.findAllByUserId(uid);

                for (CircleMember circleMember : circleMembers) {
                    if (circleMember.getCircleId().equals(tempId)) {
                        circleMemberRepository.deleteById(circleMember.getId());
                    }
                }
            }
        }
    }

    public void deletePost(String nickname, Long uid, Long deleteId) {

        Optional<CirclePosts> temp = circlePostsRepository.findById(deleteId);
        CirclePosts circlePost = temp.get();

        List<PostComment> postComments = postCommentRepository.findByPostId(circlePost.getId());
        for (PostComment postComment : postComments) {
            if (postComment.getPostId().equals(deleteId)) {
                postCommentRepository.deleteById(postComment.getId());
            }
        }

        List<PostPhoto> postPhotos = postPhotoRepository.findByPostId(circlePost.getId());
        for (PostPhoto postPhoto : postPhotos) {
            if (postPhoto.getPostId().equals(deleteId)) {
                postPhotoRepository.deleteById(postPhoto.getId());
            }
        }

        List<PostLike> postLikes = postLikeRepository.findAllByPostId(circlePost.getId());
        for (PostLike postLike : postLikes) {
            if (postLike.getPostId().equals(deleteId)) {
                postLikeRepository.deleteById(postLike.getId());
            }
        }

        circlePostsRepository.deleteById(deleteId);
    }

    public void deleteComment(Long deleteId) {

        Optional<PostComment> temp = postCommentRepository.findById(deleteId);
        PostComment postComment = temp.get();
        postCommentRepository.deleteById(postComment.getId());

    }

    public void deleteLike(Long postId, Long userId) {

        List<PostLike> temp = postLikeRepository.findAllByPostId(postId);
        for(PostLike postLike: temp){

            if(postLike.getUserId().equals(userId)){
                postLikeRepository.deleteById(postLike.getId());
            }
        }
    }
}
