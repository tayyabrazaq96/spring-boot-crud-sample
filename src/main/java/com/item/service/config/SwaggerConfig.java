package com.item.service.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${environment.host}")
  String environmentHost;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .host(environmentHost)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.hayi"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(securitySchema()))
        .securityContexts(Collections.singletonList(securityContext()));
  }

  private OAuth securitySchema() {

    List<AuthorizationScope> authorizationScopeList = new ArrayList();
    authorizationScopeList.add(new AuthorizationScope("read", "read all"));
    authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
    authorizationScopeList.add(new AuthorizationScope("write", "access all"));

    List<GrantType> grantTypes = new ArrayList();
    // TODO: Need to update url for login
    GrantType creGrant =
        new ResourceOwnerPasswordCredentialsGrant("http://" + environmentHost + "/oauth/token");

    grantTypes.add(creGrant);

    return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.ant("/**"))
        .build();
  }

  private List<SecurityReference> defaultAuth() {

    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
    authorizationScopes[0] = new AuthorizationScope("read", "read all");
    authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
    authorizationScopes[2] = new AuthorizationScope("write", "write all");

    return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
  }

  @Bean
  public SecurityConfiguration securityInfo() {
    Map<String, Object> keyValue = new HashMap<>();
    keyValue.put("deviceToken", "abc123");
    return new SecurityConfiguration("my-trusted-client", "secret", "", "", "", keyValue, true);
  }
}
