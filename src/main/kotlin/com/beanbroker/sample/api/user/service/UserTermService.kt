package com.beanbroker.sample.api.user.service

import com.beanbroker.sample.api.user.entity.UserTermEntity
import com.beanbroker.sample.api.user.repository.UserTermPredicator
import com.beanbroker.sample.api.user.repository.UserTermRepository
import com.querydsl.core.types.Predicate
import org.springframework.stereotype.Service


@Service
class UserTermService (

    private val userTermRepository: UserTermRepository
){


    fun save(userId:String, firstTerm: Char, secondTerm: Char){



        userTermRepository.save(
            UserTermEntity().apply {

                this.firstTerm = firstTerm
                this.userId = userId
                this.secondTerm = secondTerm

            }
        )
    }

    fun getUserTermByPredicator(seq : Int, firstTerm : String, secondTerm : String): UserTermEntity? {


        return userTermRepository.getByPredicator( setUserTermPredicator(
            seq = seq,
            firstTerm = firstTerm,
            secondTerm = secondTerm)
        )

    }


    private fun setUserTermPredicator(seq: Int, firstTerm: String, secondTerm: String)
     : Predicate{

        return UserTermPredicator()
            .seq(seq)
            .firstTerm(firstTerm)
            .secondTerm(secondTerm)
            .value()
    }


}