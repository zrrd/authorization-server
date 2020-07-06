package cn.worken.auth.security.dto;

import lombok.Data;

/**
 * 资源
 *
 * @author shaoyijiong
 * @date 2019/4/24
 */
@Data
public class ResDto {

    /**
     * 资源编码
     */
    private String resCd;
    /**
     * 资源类型
     */
    private String resType;
}
