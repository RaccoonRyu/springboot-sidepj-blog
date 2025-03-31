package me.ryujiheon.springbootblog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

// 테스트용 애플리케이션 컨텍스트 구성
// 메인 애플리케이션 클래스에 추가하는 애너테이션인 @ SpringBootApplication이 있는 클래스를 찾고
// 그 클래스에 포함되어 있는 빈을 찾은 다음 테스트용 애플리케이션 컨텍스트라는 것을 만듦
@SpringBootTest
// MockMvc 생성 및 자동 구성
// MockMvc를 생성하고 자동으로 구성하는 애너테이션
// MockMvc는 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공하는 유틸리티 클래스이다. (컨트롤러 테스트용)
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    // 테스트 전 실행하는 메서드
    //여기서는 MockMvcSetUp() 메서드를 실행해 MockMvc를 설정
    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // 테스트 후 실행하는 메서드
    // 여기서는 cleanUp() 메서드를 실행해 member 테이블에 있는 데이터들을 모두 삭제
    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

}