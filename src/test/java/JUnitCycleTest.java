import org.junit.jupiter.api.*;

// JUnit이 각 테스트에 대해 객체를 만들어 독립적으로 실행하는 것을 테스트하는 예제
public class JUnitCycleTest {
    @BeforeAll
    // 전체 테스트를 시작하기 전에 1회 실행하는 메서드. 전체 테스트 실행 주기에서 한 번만 호출되어야 하기 때문에 static으로 선언
    // DB연결 혹은 테스트 환경을 초기화 할 때 사용
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    // 테스트 케이스를 시작하기 전 마다 실행하는 메서드
    // 테스트 메서드에서 사용하는 객체를 초기화하거나 테스트에 필요한 값을 미리 넣을 때 사용
    // 각 인스턴스에 대해 메서드를 호출해야 하므로 메서드는 static이 아니어야 함
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll
    // 전체 테스트를 마치고 종료하기 전에 1회 실행하는 메서드. 전체 테스트 실행 주기에서 한 번만 호출되어야 하기 때문에 static으로 선언
    // 데이터베이스 연결을 종료하거나 공통적으로 사용하는 자원을 해제할 때 사용
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach // 테스트 케이스를 종료하기 전 마다 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
