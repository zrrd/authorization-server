package cn.worken.auth.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author shaoyijiong
 * @date 2020/7/5
 */
@Getter
public class ServiceException extends RuntimeException {

    private final Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public ServiceException(String message) {
        super(message);
    }
}
