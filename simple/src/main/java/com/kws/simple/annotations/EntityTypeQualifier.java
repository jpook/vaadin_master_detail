package com.kws.simple.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import com.kws.simple.model.interf.ITableEntity;

@Qualifier
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EntityTypeQualifier {
	Class<? extends ITableEntity> value();
}
