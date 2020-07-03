package cn.worken.auth.security;


import cn.worken.auth.security.dto.LoginUserInfo;
import cn.worken.auth.security.dto.UserConstants;
import java.util.Base64;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author shaoyijiong
 */
@Configuration
public class OAuthServerConfig {

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
     * 密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 认证服务器配置
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final AuthenticationManager authenticationManager;
        private final ClientDetailsServiceAdaptImpl clientDetailsServiceAdapt;

        public AuthorizationServerConfiguration(AuthenticationManager authenticationManager,
            ClientDetailsServiceAdaptImpl clientDetailsServiceAdapt) {
            this.authenticationManager = authenticationManager;
            this.clientDetailsServiceAdapt = clientDetailsServiceAdapt;
        }


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // 手动配置
            clients.withClientDetails(clientDetailsServiceAdapt);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter())
                // password 登陆方式鉴权
                .authenticationManager(authenticationManager)
                .exceptionTranslator(e -> {
                    throw e;
                })
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }


        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

        @Value("${jwt-secret-key}")
        private String stringKey;

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            // 自定义生成签名的key
            accessTokenConverter.setSigningKey(new String(Base64.getDecoder().decode(stringKey)));
            // 自定义生成的签名
            accessTokenConverter.setAccessTokenConverter(new DefaultAccessTokenConverter() {
                @Override
                public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                    Map<String, Object> map = (Map<String, Object>) super.convertAccessToken(token, authentication);
                    map.remove("user_name");
                    // password 自定义 jwt token
                    if (token instanceof DefaultOAuth2AccessToken && authentication.getPrincipal() != null
                        && authentication.getPrincipal() instanceof LoginUserInfo) {
                        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) token;
                        LoginUserInfo userInfo = (LoginUserInfo) authentication.getPrincipal();
                        map.put(UserConstants.USER_ID, userInfo.getUserId());
                        map.put(UserConstants.USER_TYPE, userInfo.getUserType());
                        map.put(UserConstants.NAME, userInfo.getUserName());
                        map.put(UserConstants.COM_ID, userInfo.getComId());
                        // 自定义 额外参数
                        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
                        additionalInformation.put("code", userInfo.getCode());
                        additionalInformation.put("message", userInfo.getMessage());
                        additionalInformation.put("data", userInfo);
                    } else {
                        // client_credentials 自定义 jwt token
                    }
                    return map;
                }
            });
            return accessTokenConverter;
        }
    }

}
