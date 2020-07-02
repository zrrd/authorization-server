package cn.worken.auth.security.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户拥有的权限
 *
 * @author shaoyijiong
 * @date 2019/4/23
 */
@Data
@NoArgsConstructor
public class ResTypeDto {

    /**
     * 菜单权限
     */
    private List<String> menu;
    /**
     * 操作权限
     */
    private List<String> opt;

    /**
     * 权限配置
     */
    public ResTypeDto(Set<ResDto> resDtoSet) {
        menu = resDtoSet.stream().filter(r -> "MENU".equals(r.getResType())).map(ResDto::getResCd)
            .collect(Collectors.toList());
        opt = resDtoSet.stream().filter(r -> "OPT".equals(r.getResType())).map(ResDto::getResCd)
            .collect(Collectors.toList());
    }

    public static ResTypeDto emptyRes() {
        ResTypeDto resTypeDto = new ResTypeDto();
        resTypeDto.setMenu(Collections.emptyList());
        resTypeDto.setOpt(Collections.emptyList());
        return resTypeDto;
    }

    @Override
    public String toString() {
        return "{" + "menu=" + menu + ", opt=" + opt + '}';
    }
}
