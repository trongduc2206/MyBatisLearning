package com.learning.mybatis.interceptor;

import com.learning.mybatis.entity.BaseEntity;
import com.learning.mybatis.entity.Product;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.Properties;
import org.apache.ibatis.executor.Executor;

import javax.swing.text.html.parser.Entity;

@Intercepts(@Signature(type = Executor.class, method = "update", args={MappedStatement.class, Object.class}))
public class BaseEntityInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // get sql
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // get parameter , this is the target object that you want to handle
        Object parameter = invocation.getArgs()[1];
        // make sure super class is BaseEntity
        if (parameter instanceof Product) {
            //init
            Product product = (Product) parameter;
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                product.createdEntityHandle();
            } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                product.updatedEntityHandle();
            }
        }

        return invocation.proceed();
    }
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

