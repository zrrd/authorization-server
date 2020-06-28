package cn.worken.auth.security;


import cn.worken.auth.service.ClientDetailsServiceAdaptImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author 徐靖峰 Date 2018-04-19
 */
@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "order";

    /**
     * 资源服务器配置
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/order/**")
                .authenticated();//配置order访问控制，必须认证过后才可以访问
        }
    }

    /**
     * 认证服务器配置
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final AuthenticationManager authenticationManager;
        private final PasswordEncoder passwordEncoder;
        private final ClientDetailsServiceAdaptImpl clientDetailsServiceAdapt;

        public AuthorizationServerConfiguration(AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            ClientDetailsServiceAdaptImpl clientDetailsServiceAdapt) {
            this.authenticationManager = authenticationManager;
            this.passwordEncoder = passwordEncoder;
            this.clientDetailsServiceAdapt = clientDetailsServiceAdapt;
        }


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // 手动配置
            clients.withClientDetails(clientDetailsServiceAdapt);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                .tokenStore(new InMemoryTokenStore())
                // password 登陆方式鉴权
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }



        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}
