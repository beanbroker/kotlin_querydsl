package com.beanbroker.sample.api.auth

import java.time.LocalDateTime


class BeanbrokerToken {

    var token: String = ""
    var expireDate: LocalDateTime? = null
}