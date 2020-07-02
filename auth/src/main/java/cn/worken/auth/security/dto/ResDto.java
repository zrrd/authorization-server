package cn.worken.auth.security.dto;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import java.util.Set;
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
    /**
     * 用户类型
     */
    private String userType;

    /**
     * 判断用户权限是否匹配 userTypeSet {0,1} resUserTypeSet{0}
     *
     * @param userTypeSet 需要匹配的用户类型
     */
    public boolean matches(Set<String> userTypeSet) {
        Set<String> resUserTypeSet = Sets.newHashSet(Splitter.on(",").split(userType));
        //判断交集是否大于0
        return !Sets.intersection(resUserTypeSet, userTypeSet).isEmpty();
    }
}
