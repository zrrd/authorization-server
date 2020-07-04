import cn.worken.auth.AuthServerApp;
import cn.worken.auth.util.RSAUtils;
import com.google.common.io.CharStreams;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

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
    public void a() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, String> keys = RSAUtils.createKeys(1024);

        File pubFile = ResourceUtils.getFile("classpath:pub.key");
        String pubString = CharStreams.toString(new InputStreamReader(new FileInputStream(pubFile)));
        System.out.println(RSAUtils.getPublicKey(pubString));



        File priFile = ResourceUtils.getFile("classpath:pri.key");
        String priString = CharStreams.toString(new InputStreamReader(new FileInputStream(priFile)));
        System.out.println(RSAUtils.getPrivateKey(priString));
    }
}
