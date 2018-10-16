//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.event;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import java.time.LocalDateTime;
import org.springframework.context.ApplicationEvent;

public class DomainEvent<T extends Domain> extends ApplicationEvent {
    private DomainEvent.Type type;
    private MetaDomain meta;
    private T domain;
    private Iterable<T> domains;
    private LocalDateTime createdTime;

    public DomainEvent(DomainEvent.Type type, MetaDomain meta, T domain) {
        super(domain);
        this.type = type;
        this.meta = meta;
        this.domain = domain;
        this.createdTime = LocalDateTime.now();
    }

    public DomainEvent(DomainEvent.Type type, MetaDomain meta, Iterable<T> domains) {
        super(domains);
        this.type = type;
        this.meta = meta;
        this.domains = domains;
        this.createdTime = LocalDateTime.now();
    }

    public DomainEvent.Type getType() {
        return this.type;
    }

    public MetaDomain getMeta() {
        return this.meta;
    }

    public T getDomain() {
        return this.domain;
    }

    public Iterable<T> getDomains() {
        return this.domains;
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public static enum Type {
        CREATE("创建"),
        BATCHCREATE("批量创建"),
        UPDATE("修改"),
        BATCHUPDATE("批量修改"),
        REMOVE("删除"),
        BATCHREMOVE("批量删除");

        String text;

        private Type(String text) {
            this.text = text;
        }
    }
}
