import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// JUnit으로 단위 테스트 코드 만드는 예제
public class JUnitTest {
    @DisplayName("1 + 3는 4이다.") // 테스트 이름을 명시하는 애너테이션
    @Test // 해당 애너테이션이 붙은 메서드는 테스트를 수행하는 메서드가 된다.
    public void junitTest() {
        int a = 1;
        int b= 2;
        int sum = 3;

        Assertions.assertEquals(sum, a + b); // 값이 같은지 확인. 첫 번째 인수에는 기댓값/두 번째 인수에는 실제 검증할 값
    }
}
