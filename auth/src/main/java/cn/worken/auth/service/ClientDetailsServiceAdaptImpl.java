package cn.worken.auth.service;

import cn.worken.auth.service.dto.CustomClientDetails;
import cn.worken.auth.service.entity.OpenApp;
import com.google.common.base.Splitter;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * client 配置
 *
 * @author shaoyijiong
 * @date 2020/6/28
 */
@Component
public class ClientDetailsServiceAdaptImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OpenApp client = OpenApp.getByClientId(clientId);
        CustomClientDetails clientDetails = new CustomClientDetails();
        clientDetails.setClientId(client.getAppKey());
        clientDetails.setClientSecret(client.getAppSecret());
        clientDetails.setComId(client.getComId());
        clientDetails.setScope(Arrays.asList("read", "write"));
        clientDetails.setAuthorizedGrantTypes(
            Splitter.on(",").splitToList(Optional.ofNullable(client.getAuthorizedGrantTypes()).orElse("")));
        clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        return clientDetails;
    }
}
