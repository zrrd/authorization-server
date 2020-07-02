package cn.worken.auth.service.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织
 * </p>
 *
 * @author jobob
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysCom extends Model<SysCom> {


    /**
     * 公司id：四位字母大写+6位随机数字
     */
    private String id;

    /**
     * 产品id
     */
    private String projectId;

    /**
     * 公司名
     */
    private String comName;

    /**
     * 行业id
     */
    private Integer industryId;

    /**
     * 所属行业名
     */
    private String industryName;

    /**
     * 销售负责人
     */
    private String saleMgr;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者ID
     */
    private Integer createUserId;

    /**
     * 创建者姓名
     */
    private String createUserName;

    /**
     * 状态 0 禁用 1 启用  9 伪删除
     */
    private Integer status;

}
