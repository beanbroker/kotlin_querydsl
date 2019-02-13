package com.beanbroker.sample.service

import com.beanbroker.sample.entity.UserEntity
import com.beanbroker.sample.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserUpdateService(
    private val userRepository: UserRepository

){

    fun updateUser(userEntity: UserEntity){
        userRepository.save(userEntity)
    }

}