package cn.worken.auth.security;

import cn.worken.auth.security.dto.LoginParam;
import cn.worken.auth.security.dto.LoginUserInfo;
import java.util.Collections;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * password 模式 用户校验
 *
 * @author shaoyijiong
 * @date 2020/7/1
 */
@Service
public class UserProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;

    public UserProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginParam loginParam = LoginParam.getLoginParamWithRequest(authentication);
        LoginUserInfo userInfo = userDetailsService.login(loginParam);
        return new UsernamePasswordAuthenticationToken(userInfo, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class == aClass;
    }
}
