package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CirclePostsServiceTest {

    private CirclePostsService circlePostsService;

    @Mock
    private CirclePostsRepository circlePostsRepository;

    @Mock
    private PostPhotoRepository postPhotoRepository;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        circlePostsService = new CirclePostsService(circlePostsRepository, postPhotoRepository);
    }

    @Test
    public void addPost(){

        Long circleId = 10L;
        Long postId = 100L;
        String author = "비켜보세요";
        String description = "test";
        List<String> photoUrl = new ArrayList<>();
        photoUrl.add("aaaa");

        CirclePosts mockUser = CirclePosts.builder()
                .circleId(circleId)
                .author(author)
                .description(description)
                .likeNum(0)
                .write_Date(ZonedDateTime.now())
                .build();

        List<PostPhoto> postPhotoList = new ArrayList<>();
        for (String str : photoUrl) {
            PostPhoto temp = new PostPhoto();
            temp.builder().id(50L).postId(mockUser.getId()).photoUrl(str).build();
            postPhotoList.add(temp);
        }
        mockUser.setPhoto(postPhotoList);

        given(circlePostsRepository.save(any())).willReturn(mockUser);

        CirclePosts circlePosts = circlePostsService.addPost(mockUser.getId(), author, description, photoUrl);

        assertThat(circlePosts.getPostPhoto()).isEqualTo(postPhotoList);
    }

    @Test
    public void formatting(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        String nowStr1 = now.format(DateTimeFormatter.ISO_DATE_TIME); // 2019-10-11T15:48:07.039+09:00[Asia/Seoul]
        String nowStr2 = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z")); // 2019/10/11 15:48:07 KST
        String nowStr3 = now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)); // 2019년 10월 11일 금요일 오후 3시 48분 07초 KST
        String nowStr4 = now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.US)); // Friday, October 11, 2019 3:48:07 PM KST

        ZonedDateTime now1 = ZonedDateTime.parse(nowStr1);
        ZonedDateTime now2 = ZonedDateTime.parse(nowStr2, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z"));
        ZonedDateTime now3 = ZonedDateTime.parse(nowStr3, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
        ZonedDateTime now4 = ZonedDateTime.parse(nowStr4, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.US));

        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now3);
        System.out.println(now4);
    }
}