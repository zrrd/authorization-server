package cn.worken.auth.security.dto;

import lombok.Data;
import org.springframework.security.core.Authentication;

/**
 * 登陆参数
 *
 * @author shaoyijiong
 * @date 2020/7/1
 */
@Data
public class LoginParam {

    private String username;
    private String password;

    public static LoginParam getLoginParamWithRequest(Authentication authentication) {
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername((String) authentication.getPrincipal());
        loginParam.setPassword((String) authentication.getCredentials());
        return loginParam;
    }
}
