package com.test.Util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Owner {
String emailId();

String testDescription() default "";

String expectedResult() default "";

String traceability() default "";

boolean isAutomated() default false;
}