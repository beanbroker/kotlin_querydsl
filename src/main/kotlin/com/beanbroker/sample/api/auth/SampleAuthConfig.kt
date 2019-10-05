package com.beanbroker.sample.api.auth

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class SampleAuthConfig(
        val tokenService: SampleTokenService
) : WebMvcConfigurer {


    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(SampleAuthInterceptor(tokenService))
    }
}