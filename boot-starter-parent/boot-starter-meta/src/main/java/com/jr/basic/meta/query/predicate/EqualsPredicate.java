//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query.predicate;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

public class EqualsPredicate implements Predicate<Object> {
    private Object value;

    public EqualsPredicate(Object value) {
        this.value = value;
    }

    public boolean apply(@Nullable Object input) {
        if (input == null) {
            return this.value == null;
        } else {
            return this.value == null ? false : this.value.equals(input);
        }
    }
}
