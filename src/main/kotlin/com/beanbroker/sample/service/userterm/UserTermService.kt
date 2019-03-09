package com.beanbroker.sample.service.userterm

import com.beanbroker.sample.entity.UserTermEntity
import com.beanbroker.sample.repository.UserTermPredicator
import com.beanbroker.sample.repository.UserTermRepository
import com.querydsl.core.types.Predicate
import org.springframework.stereotype.Service


@Service
class UserTermService (

    private val userTermRepository: UserTermRepository
){


    fun save(seq:Int, firstTerm: Char, secondTerm: Char){



        userTermRepository.save(
            UserTermEntity().apply {
                this.seq = seq
                this.firstTerm = firstTerm
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