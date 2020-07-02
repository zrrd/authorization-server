import java.util.Arrays;
import java.util.Base64;
import org.junit.Test;

/**
 * @author shaoyijiong
 * @date 2020/7/1
 */
public class Go {

    @Test
    public void a() {
        String stringKey = "KmReSFdkIVZYd2VwZjVqaHVYUmIlTXpIcm5HSzgldXM=";
        System.out.println(new String(Base64.getDecoder().decode(stringKey)));
    }
}
