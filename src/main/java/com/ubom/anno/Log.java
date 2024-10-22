package com.ubom.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// 表示在运行时保留
@Target(ElementType.METHOD)// 表示注解只能用于方法上
public @interface Log {
}
