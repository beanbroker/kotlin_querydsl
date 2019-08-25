package com.beanbroker.sample.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HealthContoller{

    @GetMapping()
    fun health() = "hello"

}