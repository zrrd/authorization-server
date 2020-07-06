package cn.worken.auth.service.mapper;

import cn.worken.auth.security.dto.ResDto;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * @author shaoyijiong
 * @date 2019/4/23
 */
public interface ResMapper {

    /**
     * 获得组织下的所有权限
     *
     * @param comId 组织id
     * @param resType 权限类型 后台 前台
     * @return 返回
     */
    Set<ResDto> getAllResUnderTheCompany(@Param("comId") String comId,
        @Param("resType") int resType);

    /**
     * 获取用户所有角色的所有权限
     *
     * @param resCdList res 资源code
     * @return 权限
     */
    Set<ResDto> getResByResCd(@Param("resCdList") Set<String> resCdList);

    /**
     * 获取该用户的所有资源
     *
     * @param userId 用户id
     * @return 资源ids
     */
    Set<String> getResCd(Integer userId);

    /**
     * 获取该组织对应的所以资源
     *
     * @param comId 组织id
     * @return 资源ids
     */
    Set<String> getResCdByCom(String comId);
}
