import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class ATest {
    @Test
    public void test() {
        for (int i=0;i<10;i++) {
            System.out.println( new RestTemplate().getForEntity("http://localhost:8080/api/hello/world?name=feign&millis=0",String.class).getStatusCode());

        }
    }
}
