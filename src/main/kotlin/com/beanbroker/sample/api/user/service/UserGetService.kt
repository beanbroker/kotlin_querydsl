package com.beanbroker.sample.api.service.user

import com.beanbroker.sample.entity.UserEntity
import com.beanbroker.sample.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserGetService(
    private val userRepository: UserRepository

){


    fun getUserByOriginJpa(seq: Int): UserEntity {

        return userRepository.getOne(seq)
    }


    fun getUserBySeq(seq: Int): UserEntity? {

       return userRepository.getBySeq(seq)

    }

    fun getUserByName(name : String){

    }


    fun getUserEmailList(): MutableList<String>? {


        return userRepository.getUserEmailList()
    }
}