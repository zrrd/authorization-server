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
 * 系统用户表
 * </p>
 *
 * @author jobob
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组织id
     */
    private String comId;

    /**
     * 登录账户
     */
    private String loginName;

    /**
     * 手机号
     */
    private String loginMobile;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别：男、女、保密
     */
    private String sex;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户类型 0- 销售 1-市场 10-管理员
     */
    private String userType;

    /**
     * 1-已认证 0-未验证
     */
    private Boolean isMobileValid;

    /**
     * 用户头像地址
     */
    private String headImgUrl;

    /**
     * 是否签署免责申明:1-已同意 0-未签署
     */
    private Boolean isAgree;

    /**
     * 同意免责申明时间
     */
    private LocalDateTime agreeTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 密码更新时间
     */
    private LocalDateTime pwdUpdateTime;

    /**
     * 上级用户id
     */
    private Integer upperUserId;

    /**
     * 部门id
     */
    private Integer departId;

    /**
     * 1-正常 0-暂停 2-审核  9 删除
     */
    private Boolean status;

    /**
     * 所属产品
     */
    private String productId;


}
