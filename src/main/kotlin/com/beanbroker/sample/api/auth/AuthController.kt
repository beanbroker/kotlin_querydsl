package com.beanbroker.sample.api.auth

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController{



    @SampleAuthorization
    @RequestMapping("/test")
    fun testGetInfo(): String {


        return "success"
    }


}