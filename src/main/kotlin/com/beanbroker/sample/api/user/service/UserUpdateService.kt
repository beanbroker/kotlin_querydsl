package com.beanbroker.sample.api.user.service

import com.beanbroker.sample.api.user.entity.UserEntity
import com.beanbroker.sample.api.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserUpdateService(
    private val userRepository: UserRepository

){

    fun updateUser(userEntity: UserEntity){
        userRepository.save(userEntity)
    }

}