package cn.worken.auth.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 开放平台企业应用
 * </p>
 *
 * @author jobob
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OpenApp implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String comId;

    /**
     * 应用名
     */
    private String appName;

    /**
     * app_key
     */
    private String appKey;

    /**
     * App Secret
     */
    private String appSecret;

    /**
     * 认证token失效时长
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新token失效时长
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 认证类型
     */
    private String authorizedGrantTypes;

    /**
     * 创建者ID
     */
    private Integer createUserId;

    /**
     * 创建者姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态 0 草稿 1 上线 2-申请 -1 审批拒绝  9 伪删除
     */
    private Boolean status;


}
