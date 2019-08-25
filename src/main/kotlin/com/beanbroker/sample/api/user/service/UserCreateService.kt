package com.beanbroker.sample.api.user.service

import com.beanbroker.sample.api.user.entity.UserEntity
import com.beanbroker.sample.api.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserCreateService(
    private val userRepository: UserRepository

){

    @Transactional
    fun saveUser(userEntity: UserEntity): UserEntity {
        return userRepository.save(userEntity)
    }

}