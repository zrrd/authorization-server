package cn.worken.auth.service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录日志
 * </p>
 *
 * @author jobob
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserLoginLog implements Serializable {

    /**
     * 登录ID（登录失败情况不存该值）
     */
    private Integer userId;

    /**
     * 登录名
     */
    private String loginMobile;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录终端：ANDROID-移动端 IOS-IOS端 PC-WEB版 MP-小程序
     */
    private String platform;

    /**
     * 终端版本号
     */
    private String platformVersion;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 1-成功 -1-失败
     */
    private Boolean status;

    /**
     * 接口返回结果
     */
    private String result;

    private String oldId;

    private String etlId;


}
