package me.ryujiheon.springbootblog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    // 테스트 전 실행하는 메서드
    // 여기서는 MockMvcSetUp() 메서드를 실행해 MockMvc를 설정
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

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given - 멤버 저장
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when - 멤버 리스트 조회 API 호출
        // perform() : 요청을 전송하는 메서드. 결과로 ResultActions 객체를 받는다.
        // ResultActions : 반환값을 검증하고 확인하는 andExpect() 메서드를 제공한다.
        // accept() : 요청을 보낼 때 어떤 타입으로 응답 받을지 결정하는 메서드(여기서는 JSON)
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then - 응답 코드가 200 OK이고 반환 받은 값 중 0번째 요소의 id, name이 저장된 값과 같은지 확인
        // andExpect() : 응답을 검증하는 메서드.
        // TestController에서 만든 API는 응답으로 OK(200)을 반환하므로 이에 해당하는 메서드인 isOk()를 사용해 응답 코드가 OK(200)인지 확인
        result.andExpect(status().isOk())
                // 응답의 0번째 값이 DB에 저장한 값과 같은지 확인
                // jsonPath("$[0].${필드명}") : JSON 응답값의 값을 가져오는 역할을 하는 메서드
                // 0번째 배열에 들어있는 객체의 id, name값을 가져오고 저장된 값과 같은지 확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

    // given-when-then
    /*
    given - 테스트를 준비하는 부분
    when - 테스트를 실제로 진행하는 부분
    then - 테스트 결과를 검증하는 부분
     */
}