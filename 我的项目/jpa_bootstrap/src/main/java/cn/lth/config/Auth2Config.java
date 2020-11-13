//package cn.lth.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import javax.sql.DataSource;
//
////@Configuration
////@EnableAuthorizationServer
//public class Auth2Config extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients();
//        security.tokenKeyAccess("isAuth");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.accessTokenConverter(jwtAccessTokenConverter());
//        endpoints.tokenStore(jwtTokenStore());
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////$2a$10$zymHqRKuY2vRER5.ZBZN1u75DOsydI3nG8iujJbCyherjGsdhi/ve
////        clients.jdbc(dataSource);
//        clients
//
//                .inMemory()
//                .withClient("clientapp")
//                .secret("123456")
//                .redirectUris("http://localhost:8080/")
//                .authorizedGrantTypes("authorization_code").scopes("read");
//    }
//
//    @Bean
//    public JwtTokenStore jwtTokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey("liusir");
//        return jwtAccessTokenConverter;
//    }
//}
