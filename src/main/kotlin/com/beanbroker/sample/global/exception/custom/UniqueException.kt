package com.beanbroker.sample.global.exception.custom

import com.beanbroker.sample.global.exception.BeanbrokerException

class UniqueException(

        statusCode: Int,
        errCode: String?,
        errMessage: String?

) : BeanbrokerException() {

    override var statusCode = statusCode
    override var errCode = errCode
    override var errMessage = errMessage

}