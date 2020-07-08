package cn.worken.auth.security;


import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 徐靖峰 Date 2018-04-19
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserProvider userProvider;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
        UserProvider userProvider) {
        this.userDetailsService = userDetailsService;
        this.userProvider = userProvider;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }

    /**
     * 校验用户"账户密码"
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(ImmutableList.of(userProvider));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers().anyRequest()
            .and()
            .authorizeRequests()
            .antMatchers("/oauth/**","/api/export").permitAll();
    }
}
