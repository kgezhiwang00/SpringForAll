//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query;

import com.jr.basic.meta.enums.Aggregate;
import java.beans.ConstructorProperties;
import lombok.NonNull;

public class Select {
    @NonNull
    private String name;
    private Aggregate aggregate;

    public boolean isAggregate() {
        return this.aggregate != null;
    }

    public String alias() {
        return this.aggregate == null ? this.name : this.name + "_" + this.aggregate.name();
    }

    @ConstructorProperties({"name"})
    public Select(@NonNull String name) {
        if (name == null) {
            throw new NullPointerException("name");
        } else {
            this.name = name;
        }
    }

    @ConstructorProperties({"name", "aggregate"})
    public Select(@NonNull String name, Aggregate aggregate) {
        if (name == null) {
            throw new NullPointerException("name");
        } else {
            this.name = name;
            this.aggregate = aggregate;
        }
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public Aggregate getAggregate() {
        return this.aggregate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Select)) {
            return false;
        } else {
            Select other = (Select)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$aggregate = this.getAggregate();
                Object other$aggregate = other.getAggregate();
                if (this$aggregate == null) {
                    if (other$aggregate != null) {
                        return false;
                    }
                } else if (!this$aggregate.equals(other$aggregate)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Select;
    }


}
