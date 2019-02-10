package com.beanbroker.sample.service

import com.beanbroker.sample.entity.UserEntity
import com.beanbroker.sample.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreateService(
    private val userRepository: UserRepository

){

    fun saveUser(userEntity: UserEntity){
        userRepository.save(userEntity)
    }

}