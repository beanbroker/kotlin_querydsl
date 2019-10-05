package com.beanbroker.sample.api.auth

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.RuntimeException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SampleAuthInterceptor(
        val tokenService: SampleTokenService
) : HandlerInterceptor {

    companion object {

        val HEADER_NAME = "X-token"

    }


    private val HEADER_NAME = "X-token"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return true
        val handlerMethod = handler

        if(!handlerMethod.hasMethodAnnotation(SampleAuthorization::class.java))
            return true

        val token = request.getHeader(HEADER_NAME)

        token?.let {
            tokenService.decodeToken(it)
        }?: throw RuntimeException("없으면 앙대유")
        return true
    }
}