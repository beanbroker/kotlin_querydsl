package com.beanbroker.sample.api.user

import com.beanbroker.sample.api.user.entity.UserEntity
import com.beanbroker.sample.api.user.service.UserCreateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(
        val userCreateService: UserCreateService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun userCreate() {
        val saveUser = userCreateService.saveUser(userEntity = UserEntity().apply {

            this.name = "박기진"
            this.age = 20
            this.gender = "M"
            this.userId = "tes"
            this.email = "te@saf.com"
        })


//        userCreateService.saveUser(saveUser.apply {
//            this.age = 25
//        })
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun testLogin(){

    }


}