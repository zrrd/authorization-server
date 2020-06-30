import cn.worken.auth.AuthServerApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shaoyijiong
 * @date 2020/6/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApp.class)
@ActiveProfiles("dev")
public class SpringTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void a() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}
