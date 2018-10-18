//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.common;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class GraphQLConfig {
    @Value("${graphql.queryType:QueryType}")
    private String queryType;
    @Value("${graphql.mutationType:MutationType}")
    private String mutationType;
    @Value("${graphql.split:_}")
    private String split = "_";
    @Value("${graphql.conf.path:/graphql/}")
    private String rootPath;
    @Value("${graphql.conf.dir:classpath:/graphql/}")
    private Resource schemas;
    @Value("${graphql.domainSchema:classpath:/graphql/domain.graphqls}")
    private Resource domainSchemas;
    @Value("${graphql.metaSchema:classpath:/graphql/meta.graphqls}")
    private Resource metaSchemas;
    @Value("${graphql.voSchema:classpath:/graphql/vo.graphqls}")
    private Resource voSchemas;
    @Value("${graphql.serviceSchema:classpath:/graphql/service.graphqls}")
    private Resource[] serviceSchemas;

    public GraphQLConfig() {
    }

    public String getQueryType() {
        return this.queryType;
    }

    public String getMutationType() {
        return this.mutationType;
    }

    public String getSplit() {
        return this.split;
    }

    public String getRootPath() {
        return this.rootPath;
    }

    public Resource getSchemas() {
        return this.schemas;
    }

    public Resource getDomainSchemas() {
        return this.domainSchemas;
    }

    public Resource getMetaSchemas() {
        return this.metaSchemas;
    }

    public Resource getVoSchemas() {
        return this.voSchemas;
    }

    public Resource[] getServiceSchemas() {
        return this.serviceSchemas;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setMutationType(String mutationType) {
        this.mutationType = mutationType;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public void setSchemas(Resource schemas) {
        this.schemas = schemas;
    }

    public void setDomainSchemas(Resource domainSchemas) {
        this.domainSchemas = domainSchemas;
    }

    public void setMetaSchemas(Resource metaSchemas) {
        this.metaSchemas = metaSchemas;
    }

    public void setVoSchemas(Resource voSchemas) {
        this.voSchemas = voSchemas;
    }

    public void setServiceSchemas(Resource[] serviceSchemas) {
        this.serviceSchemas = serviceSchemas;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof GraphQLConfig)) {
            return false;
        } else {
            GraphQLConfig other = (GraphQLConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label111: {
                    Object this$queryType = this.getQueryType();
                    Object other$queryType = other.getQueryType();
                    if (this$queryType == null) {
                        if (other$queryType == null) {
                            break label111;
                        }
                    } else if (this$queryType.equals(other$queryType)) {
                        break label111;
                    }

                    return false;
                }

                Object this$mutationType = this.getMutationType();
                Object other$mutationType = other.getMutationType();
                if (this$mutationType == null) {
                    if (other$mutationType != null) {
                        return false;
                    }
                } else if (!this$mutationType.equals(other$mutationType)) {
                    return false;
                }

                Object this$split = this.getSplit();
                Object other$split = other.getSplit();
                if (this$split == null) {
                    if (other$split != null) {
                        return false;
                    }
                } else if (!this$split.equals(other$split)) {
                    return false;
                }

                label90: {
                    Object this$rootPath = this.getRootPath();
                    Object other$rootPath = other.getRootPath();
                    if (this$rootPath == null) {
                        if (other$rootPath == null) {
                            break label90;
                        }
                    } else if (this$rootPath.equals(other$rootPath)) {
                        break label90;
                    }

                    return false;
                }

                label83: {
                    Object this$schemas = this.getSchemas();
                    Object other$schemas = other.getSchemas();
                    if (this$schemas == null) {
                        if (other$schemas == null) {
                            break label83;
                        }
                    } else if (this$schemas.equals(other$schemas)) {
                        break label83;
                    }

                    return false;
                }

                Object this$domainSchemas = this.getDomainSchemas();
                Object other$domainSchemas = other.getDomainSchemas();
                if (this$domainSchemas == null) {
                    if (other$domainSchemas != null) {
                        return false;
                    }
                } else if (!this$domainSchemas.equals(other$domainSchemas)) {
                    return false;
                }

                Object this$metaSchemas = this.getMetaSchemas();
                Object other$metaSchemas = other.getMetaSchemas();
                if (this$metaSchemas == null) {
                    if (other$metaSchemas != null) {
                        return false;
                    }
                } else if (!this$metaSchemas.equals(other$metaSchemas)) {
                    return false;
                }

                label62: {
                    Object this$voSchemas = this.getVoSchemas();
                    Object other$voSchemas = other.getVoSchemas();
                    if (this$voSchemas == null) {
                        if (other$voSchemas == null) {
                            break label62;
                        }
                    } else if (this$voSchemas.equals(other$voSchemas)) {
                        break label62;
                    }

                    return false;
                }

                if (!Arrays.deepEquals(this.getServiceSchemas(), other.getServiceSchemas())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof GraphQLConfig;
    }


    public String toString() {
        return "GraphQLConfig(queryType=" + this.getQueryType() + ", mutationType=" + this.getMutationType() + ", split=" + this.getSplit() + ", rootPath=" + this.getRootPath() + ", schemas=" + this.getSchemas() + ", domainSchemas=" + this.getDomainSchemas() + ", metaSchemas=" + this.getMetaSchemas() + ", voSchemas=" + this.getVoSchemas() + ", serviceSchemas=" + Arrays.deepToString(this.getServiceSchemas()) + ")";
    }
}
