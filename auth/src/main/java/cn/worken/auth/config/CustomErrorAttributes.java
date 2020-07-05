package cn.worken.auth.config;

import cn.worken.auth.security.exception.ServiceException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * 自定义全局异常处理 (spring security 异常不能用 )
 *
 * @author shaoyijiong
 * @date 2020/7/2
 */
@Slf4j
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Throwable error = getError(webRequest);
        log.error("", error);
        Map<String, Object> resp = new HashMap<>(2);
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = "系统异常!";
        // 异常判断处理
        if (error instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) error;
            code = serviceException.getCode();
            message = serviceException.getMessage();
        } else if (error instanceof OAuth2Exception) {
            code = HttpStatus.UNAUTHORIZED.value();
            message = "认证失败";
        }
        // 封装参数
        resp.put("code", code);
        resp.put("message", message);
        return resp;
    }
}
