package com.beanbroker.sample.auth


import com.beanbroker.sample.api.auth.SampleTokenService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SampleAuthTests {


    @Autowired
    private lateinit var tokenService: SampleTokenService


    @Test
    fun createToken() {


        val token = tokenService.createToken(1000, "USER")


        println(token)


    }


    @Test
    fun decodeToken(){

//      tokenService.decodeToken(tokenService.createToken(1000, "USER")!!)



    }
}

