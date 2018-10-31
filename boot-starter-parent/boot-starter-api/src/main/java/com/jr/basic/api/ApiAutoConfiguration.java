//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(
    name = {"app.api.enable"},
    havingValue = "true"
)
@Configuration
@AutoConfigureAfter({MetaAutoConfiguration.class})
public class ApiAutoConfiguration {
    public ApiAutoConfiguration() {
    }

    @Bean
    public SysApiManagerService apiDefineScan() {
        return new SysApiManagerServiceImpl();
    }

    @Bean
    public GraphQLController grqphqlController() {
        return new GraphQLController();
    }

    @Bean
    public ServiceUtils serviceUtils() {
        return new ServiceUtils();
    }

    @Bean
    public ControllerExceptionHandler controlerHandler() {
        return new ControllerExceptionHandler();
    }

    @Bean
    public DomainListFetcher domainListFetcher() {
        return new DomainListFetcher();
    }

    @Bean
    public DomainCountFetcher domainCountFetcher() {
        return new DomainCountFetcher();
    }

    @Bean
    public DomainLoadFetcher domainLoadFetcher() {
        return new DomainLoadFetcher();
    }

    @Bean
    public DomainCreateFetcher domainCreateFetcher() {
        return new DomainCreateFetcher();
    }

    @Bean
    public DomainUpdateFetcher domainUpdateFetcher() {
        return new DomainUpdateFetcher();
    }

    @Bean
    public DomainDeleteFetcher domainDeleteFetcher() {
        return new DomainDeleteFetcher();
    }

    @Bean
    public DomainImportFetcher domainImportFetcher() {
        return new DomainImportFetcher();
    }

    @Bean
    public DomainAggregateFetcher domainAggregateFetcher() {
        return new DomainAggregateFetcher();
    }

    @Bean
    public DomainFieldVisibility domainFieldVisibility() {
        return new DomainFieldVisibility();
    }

    @Bean
    public GraphQLConfig graphQLConfig() {
        return new GraphQLConfig();
    }

    @Bean
    @ConditionalOnProperty(
        name = {"application.api.security"},
        havingValue = "true",
        matchIfMissing = false
    )
    public ServiceAspect securityAspect() {
        return new ServiceAspect();
    }
}
