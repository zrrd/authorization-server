package cn.worken.auth.security.dto;

import java.util.Collections;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

/**
 * @author shaoyijiong
 * @date 2020/7/1
 */
@Getter
@Setter
public class UserAuthenticationDetails extends User {

    private Integer userId;
    private String comId;

    public UserAuthenticationDetails(String username, Integer userId, String comId) {
        super(username, "", Collections.emptyList());
        this.userId = userId;
        this.comId = comId;
    }
}
