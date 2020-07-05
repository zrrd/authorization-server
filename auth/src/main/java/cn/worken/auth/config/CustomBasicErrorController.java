package cn.worken.auth.config;

import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常处理
 *
 * @author shaoyijiong
 * @date 2020/7/3
 */
@Component
public class CustomBasicErrorController extends BasicErrorController {

    public CustomBasicErrorController(ErrorAttributes errorAttributes,
        ObjectProvider<ErrorViewResolver> errorViewResolvers, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError(),
            errorViewResolvers.orderedStream().collect(Collectors.toList()));
    }

    @Override
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        // 将http响应码修改为ok
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        return new ResponseEntity<>(body, status);
    }

}
