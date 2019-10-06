package com.beanbroker.sample.global.exception


open class BeanbrokerException : RuntimeException() {

    open var statusCode: Int = 0
    open var errCode: String? = "UNDEFINED_CODE"
    open var errMessage: String? = "UNDEFINED_MESSAGE"


}