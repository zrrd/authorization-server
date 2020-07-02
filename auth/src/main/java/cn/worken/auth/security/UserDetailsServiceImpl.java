package cn.worken.auth.security;

import cn.worken.auth.security.dto.LoginParam;
import cn.worken.auth.security.dto.LoginUserInfo;
import cn.worken.auth.security.dto.ResTypeDto;
import cn.worken.auth.security.dto.UserAuthenticationDetails;
import cn.worken.auth.service.entity.SysCom;
import cn.worken.auth.service.entity.SysUser;
import cn.worken.commons.errors.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author shaoyijiong
 * @date 2020/6/30
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = SysUser.loadUserByUsername(username);
        return new UserAuthenticationDetails(sysUser.getLoginName(), sysUser.getId(), sysUser.getComId());
    }

    /**
     * 实际用户登陆
     *
     * @return 返回给前端的用户信息
     */
    public LoginUserInfo login(LoginParam loginParam) {
        // 查找用户
        SysUser sysUser = SysUser.loadUserByUsername(loginParam.getUsername());
        if (sysUser == null) {
            throw new ServiceException("该用户不存在!");
        }
        SysCom sysCom = new SysCom().selectById(sysUser.getId());
        // 校验密码
        if (!passwordEncoder.matches(loginParam.getPassword(), sysUser.getLoginPwd())) {
            throw new ServiceException("账户密码错误，请重新输入!");
        }
        // TODO 用户权限查找 & cache

        // TODO 删除该用户cache

        // 封装前台响应
        return LoginUserInfo.builder().userId(sysUser.getId()).userName(sysUser.getUserName())
            .comId(sysUser.getComId()).companyName(sysCom.getComName()).res(ResTypeDto.emptyRes())
            .build();
    }

}
