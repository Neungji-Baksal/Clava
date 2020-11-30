package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CircleMemberServiceTest {

    private CircleMemberService circleMemberService;

    @Mock
    private CircleMemberRepository circleMemberRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        circleMemberService = new CircleMemberService(circleMemberRepository, userRepository);
    }

        /*
        CircleMember circleMember = new CircleMember();

        Optional<User> mockUser = userRepository.findByNickname(userNickname);
        User temp = mockUser.get();
        Long userId = temp.getId();

        circleMember.setCircleId(circleId);
        circleMember.setUserId(userId);
        circleMember.setLevel(1L);
        return circleMemberRepository.save(circleMember);
         */
        @Test
        public void addMember(){

            Long userId = 10L;
            Long circleId = 100L;

            CircleMember mockUser = CircleMember.builder()
                    .circleId(circleId)
                    .userId(userId)
                    .level(1L)
                    .build();

            given(circleMemberRepository.save(any())).willReturn(mockUser);

            assertThat(mockUser.getUserId()).isEqualTo(10L);
        }

}