//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain.userType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class ArrayStringUserType implements UserType {
    protected static final int[] SQL_TYPES = new int[]{12};

    public ArrayStringUserType() {
    }

    public Object assemble(Serializable arg0, Object arg1) throws HibernateException {
        return arg0;
    }

    public Object deepCopy(Object obj) throws HibernateException {
        return obj;
    }

    public Serializable disassemble(Object arg0) throws HibernateException {
        return null;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        } else {
            return x != null && y != null ? x.equals(y) : false;
        }
    }

    public int hashCode(Object obj) throws HibernateException {
        return obj.hashCode();
    }

    public boolean isMutable() {
        return false;
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String array = resultSet.getString(names[0]);
        return StringUtils.isBlank(array) ? null : array.split(",");
    }

    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            statement.setNull(index, SQL_TYPES[0]);
        } else {
            String[] array = (String[])((String[])value);
            statement.setString(index, StringUtils.join(array, ","));
        }

    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    public Class returnedClass() {
        return String[].class;
    }

    public int[] sqlTypes() {
        return SQL_TYPES;
    }
}
