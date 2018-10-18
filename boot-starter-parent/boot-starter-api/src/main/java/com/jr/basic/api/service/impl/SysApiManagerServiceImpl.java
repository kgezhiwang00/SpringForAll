//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.api.annotation.SysApi;
import com.jr.basic.api.annotation.SysService;
import com.jr.basic.api.common.GraphQLConfig;
import com.jr.basic.api.common.scalar.GraphQLLocalDate;
import com.jr.basic.api.common.scalar.GraphQLLocalDateTime;
import com.jr.basic.api.common.scalar.GraphQLLocalTime;
import com.jr.basic.api.exception.GraphqlExecuteException;
import com.jr.basic.api.service.SysApiManagerService;
import com.jr.basic.api.service.directive.AuthenticateDirective;
import com.jr.basic.api.vo.SysApiDefine;
import com.jr.basic.api.vo.SysApiParam;
import com.jr.basic.api.vo.SysApiDefine.SysApiDefineBuilder;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.exception.NoAuthException;
import com.jr.basic.meta.exception.PermissionException;
import com.jr.basic.meta.exception.ServiceException;
import com.jr.basic.meta.service.AuthenticateService;
import com.jr.basic.meta.service.MetaService;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.RuntimeWiring.Builder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

public class SysApiManagerServiceImpl implements SysApiManagerService, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(SysApiManagerServiceImpl.class);
    private GraphQLSchema schema;
    private GraphQL graphQL;
    @Autowired
    private GraphQLConfig config;
    @Autowired
    private MetaService metaService;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private Map<String, DataFetcher> fetchers;
    @Autowired
    private DomainFieldVisibility domainFieldVisibility;
    @Autowired
    private AuthenticateService authenticateService;
    private ImmutableSet<SysApiDefine> defines;

    public SysApiManagerServiceImpl() {
    }

    public List<SysApiDefine> findAll() {
        return this.defines.asList();
    }

    public SysApiDefine getByName(String name) {
        return (SysApiDefine)this.defines.stream().filter((d) -> {
            return StringUtils.equals(name, d.getName());
        }).findFirst().orElse(null);
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if (context.getParent() == null) {
            SchemaParser parser = new SchemaParser();
            SchemaGenerator generator = new SchemaGenerator();
            TypeDefinitionRegistry registry = new TypeDefinitionRegistry();

            try {
                registry.merge(parser.parse(new InputStreamReader(this.config.getDomainSchemas().getInputStream())));
                registry.merge(parser.parse(new InputStreamReader(this.config.getMetaSchemas().getInputStream())));
                registry.merge(parser.parse(new InputStreamReader(this.config.getVoSchemas().getInputStream())));
                Resource[] var6 = this.config.getServiceSchemas();
                int var7 = var6.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    Resource r = var6[var8];
                    registry.merge(parser.parse(new InputStreamReader(r.getInputStream())));
                }

                GraphQLSchema schema = generator.makeExecutableSchema(registry, this.loadRuntimeWiring(context));
                Cache<String, PreparsedDocumentEntry> cache = Caffeine.newBuilder().maximumSize(10000L).build();
                this.graphQL = GraphQL.newGraphQL(schema).build();
            } catch (IOException var10) {
                log.error("fiailed to load domain scheam by {} ", var10.getMessage());
            }
        }

    }

    private RuntimeWiring loadRuntimeWiring(ApplicationContext context) {
        Builder builder = RuntimeWiring.newRuntimeWiring().scalar(new GraphQLLocalDateTime()).scalar(new GraphQLLocalDate()).scalar(new GraphQLLocalTime()).directive("auth", new AuthenticateDirective());
        this.metaService.findAll().forEach((meta) -> {
            this.fetchers.forEach((key, fetcher) -> {
                Fetcher anno = (Fetcher)fetcher.getClass().getAnnotation(Fetcher.class);
                if (anno.isMeta()) {
                    String name = StringUtils.isBlank(anno.value()) ? meta.getName() : meta.getName() + this.config.getSplit() + anno.value();
                    builder.type(!anno.isMutation() ? this.config.getQueryType() : this.config.getMutationType(), (typeWiring) -> {
                        return typeWiring.dataFetcher(name, fetcher);
                    });
                }

            });
        });
        this.fetchers.forEach((key, fetcher) -> {
            Fetcher anno = (Fetcher)fetcher.getClass().getAnnotation(Fetcher.class);
            if (!anno.isMeta()) {
                builder.type(!anno.isMutation() ? this.config.getQueryType() : this.config.getMutationType(), (typeWiring) -> {
                    return typeWiring.dataFetcher(anno.value(), fetcher);
                });
            }

        });
        builder.type(this.config.getQueryType(), (typeWiring) -> {
            return typeWiring.dataFetcher(StringUtils.uncapitalize(ClassUtils.getShortName(MetaDomain.class)), (env) -> {
                String value = (String)env.getArgument("name");
                Preconditions.checkNotNull(value, "need load meta with name");
                return this.metaService.getByName(value);
            });
        });
        this.resolveApi(builder, context.getBeansWithAnnotation(SysService.class).values());
        builder.fieldVisibility(this.domainFieldVisibility);
        return builder.build();
    }

    protected void resolveApi(Builder wiringBuilder, Collection<Object> beans) {
        com.google.common.collect.ImmutableSet.Builder<SysApiDefine> builder = ImmutableSet.builder();
        Iterator var4 = beans.iterator();

        while(var4.hasNext()) {
            Object bean = var4.next();
            Class<?> beanClass = bean.getClass();
            if (AopUtils.isAopProxy(bean)) {
                beanClass = AopUtils.getTargetClass(bean);
            }

            SysService sysService = (SysService)beanClass.getAnnotation(SysService.class);
            Method[] var8 = beanClass.getMethods();
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Method method = var8[var10];
                SysApi api = (SysApi)method.getAnnotation(SysApi.class);
                if (api != null) {
                    SysApiDefineBuilder defineBuilder = SysApiDefine.builder().module(sysService.module()).name(sysService.name()).remark(sysService.remark()).isGraphql(api.isGraphql()).isLog(api.isLog()).isMutation(api.isMutation()).roles(api.roles());
                    LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
                    Parameter[] params = method.getParameters();
                    String[] paramNames = discoverer.getParameterNames(method);
                    if (params.length > 0) {
                        com.google.common.collect.ImmutableList.Builder<SysApiParam> apiParams = ImmutableList.builder();
                        int i = 0;

                        for(int len = params.length; i < len; ++i) {
                            Parameter param = params[i];
                            String paramName = paramNames[i];
                            apiParams.add(SysApiParam.builder().name(paramName).type(param.getType().getName()).orderNum(i).build());
                        }

                        defineBuilder.params(apiParams.build());
                    }

                    if (api.isGraphql()) {
                        String type = this.config.getQueryType();
                        if (api.isMutation()) {
                            type = this.config.getMutationType();
                        }

                        wiringBuilder.type(type, (typeWiring) -> {
                            return typeWiring.dataFetcher(api.name(), (env) -> {
                                Map<String, Object> args = env.getArguments();
                                Object[] argValues = new Object[params.length];
                                int i = 0;

                                for(int len = params.length; i < len; ++i) {
                                    argValues[i] = args.get(paramNames[i]);
                                }

                                try {
                                    return method.invoke(bean, argValues);
                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                    Throwable cause = Throwables.getRootCause(e);
                                    if (cause instanceof ServiceException) {
                                        throw (ServiceException)cause;
                                    } else {
                                        throw new ServiceException(cause);
                                    }
                                }
                            });
                        });
                    }

                    builder.add(defineBuilder.build());
                }
            }
        }

        this.defines = builder.build();
    }

    public ExecutionResult execute(String query) {
        graphql.ExecutionInput.Builder inputBuilder = ExecutionInput.newExecutionInput();
        inputBuilder.query(query);
        if (this.authenticateService != null) {
            inputBuilder.context(ImmutableMap.of("authenticateService", this.authenticateService));
        }

        ExecutionResult result = this.graphQL.execute(inputBuilder.build());
        if (result.getErrors().size() > 0) {
            if (log.isErrorEnabled()) {
                log.error("error when exec graphql {} " + result.getErrors().toString());
            }

            Iterator var4 = result.getErrors().iterator();

            while(var4.hasNext()) {
                GraphQLError e = (GraphQLError)var4.next();
                if (e.getErrorType().equals(ErrorType.DataFetchingException)) {
                    ExceptionWhileDataFetching de = (ExceptionWhileDataFetching)e;
                    Throwable t = de.getException();
                    if (t instanceof ServiceException && t.getCause() != null) {
                        t = t.getCause();
                    }

                    if (t instanceof PermissionException) {
                        throw (PermissionException)t;
                    }

                    if (t instanceof ConstraintViolationException) {
                        throw (ConstraintViolationException)t;
                    }

                    if (t instanceof NoAuthException) {
                        throw (NoAuthException)t;
                    }
                }
            }

            throw new GraphqlExecuteException(result.getErrors());
        } else {
            return result;
        }
    }
}
