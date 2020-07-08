package cn.worken.auth.security.advice;

import java.util.Optional;
import javax.servlet.http.Cookie;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * token 颁布完处理
 *
 * @author shaoyijiong
 * @date 2020/7/7
 */
@Component
@Aspect
public class TokenCreateAdvice {

    @AfterReturning(pointcut = "execution(* org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter.enhance(..))", returning = "accessToken")
    public void afterTokenPublish(OAuth2AccessToken accessToken) {
        setCookie(accessToken);

    }


    /**
     * 设置cookie
     */
    private void setCookie(OAuth2AccessToken token) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        if (requestAttributes != null) {
            Cookie cookie = new Cookie("token", token.getValue());
            cookie.setMaxAge(token.getExpiresIn());
            cookie.setHttpOnly(false);
            //设置根路径
            cookie.setPath("/");
            Optional.ofNullable(requestAttributes.getResponse()).ifPresent(r -> r.addCookie(cookie));
        }
    }
}
