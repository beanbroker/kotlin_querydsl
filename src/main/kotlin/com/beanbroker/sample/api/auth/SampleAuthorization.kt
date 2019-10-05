package com.beanbroker.sample.api.auth


import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.annotation.Target

@Target(AnnotationTarget.FUNCTION)
@Retention(RetentionPolicy.RUNTIME)
annotation class SampleAuthorization(
        val data: Int = 0
)