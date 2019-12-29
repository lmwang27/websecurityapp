package com.tutorialspoint.websecurityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * Now, define the OAuth2 Configuration class to add the Client ID, Client Secret,
 * Define the JwtAccessTokenConverter, Private key and Public key for token signer key
 * and verifier key, and configure the ClientDetailsServiceConfigurer for the Token validity with scopes.
 **/
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
    private String clientid = "a54d886c-f4ed-49a5-845d-a1c439ab7f35";
    private String clientSecret = "4518a294-22ad-4d28-b409-5fab8f3f42f4";
    private String privateKey ="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIBOwIBAAJBANvjpSS4lTw1l6VLC8/teT4Tafjje2pLMy6FWvhSbb+N7Akswam9\n" +
            "jRZc+fm6QHy3I60mBqRSB0A+OerhbAmBNoUCAwEAAQJBALUabFSvNjKtzSVVhvgN\n" +
            "FbOb18cmzOoB37isGv21HjEKEoTVbc6AZ4AFf0cj8I37yANapeZen+7c7h5/maZJ\n" +
            "d60CIQD6F5rLIgaAimIGa0CtGwq7L8Tw42TvR9QmDac5wt7AHwIhAOEVZHoVzWp1\n" +
            "/2JrmHnrA0Mp4Tp6CwiC6vjApN/mQaTbAiBu4/aD6XNenX13gSjtWJc6hqTZdspz\n" +
            "erLrqJQkfW6NWwIhAM2I4GCPai2OJK/1+p1SGn+JTa89ZLfX/MM7/xKpy0XDAiBb\n" +
            "RKAgbiPqdn0NieKtUuL+6GcKrN+EiDz+RbOLU/aWGQ==\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANvjpSS4lTw1l6VLC8/teT4Tafjje2pL\n" +
            "My6FWvhSbb+N7Akswam9jRZc+fm6QHy3I60mBqRSB0A+OerhbAmBNoUCAwEAAQ==\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
