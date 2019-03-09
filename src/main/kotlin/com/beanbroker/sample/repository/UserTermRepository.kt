package com.beanbroker.sample.repository

import com.beanbroker.sample.entity.QUserTermEntity
import com.beanbroker.sample.entity.UserTermEntity
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport


interface UserTermRepositoryCustom{

    fun getByPredicator(predicate: Predicate): UserTermEntity?
    fun findAllByPredicator(predicate: Predicate): List<UserTermEntity>?

}

interface UserTermRepository : JpaRepository<UserTermEntity, Int>, UserTermRepositoryCustom

class UserTermRepositoryImpl :
    QuerydslRepositorySupport(UserTermEntity::class.java),UserTermRepositoryCustom{


    override fun getByPredicator(predicate: Predicate): UserTermEntity? {

        val table = QUserTermEntity.userTermEntity
        return from(table)
            .where(predicate)
            .fetchOne()
    }


    override fun findAllByPredicator(predicate: Predicate): List<UserTermEntity>? {

        val table = QUserTermEntity.userTermEntity
        return from(table)
            .where(predicate)
            .fetch()

    }


}


class UserTermPredicator{

    companion object {
        private val table = QUserTermEntity.userTermEntity
        private const val SEPARATOR = ","
        private val AGREEMENT_YN = charArrayOf('Y', 'N', 'R')
    }

    private var builder = BooleanBuilder()


    fun seq(seq: Int?): UserTermPredicator {
        if (seq != null && seq > 0)
            builder.and(table.seq.eq(seq))
        return this
    }


    fun firstTerm(firstTerm: String?): UserTermPredicator {
        if (firstTerm.isNullOrEmpty()) return this
        if (firstTerm.contains(SEPARATOR)) {
            val condition = firstTerm.split(SEPARATOR)
                .filter { v -> v.isNotEmpty() && firstTerm.contains(v.single()) }
                .map { v -> v.single() }
            if (condition.isEmpty()) return this
            builder.and(table.firstTerm.`in`(condition))
        } else {
            if (AGREEMENT_YN.contains(firstTerm.first())) builder.and(table.firstTerm.eq(firstTerm.first()))
        }
        return this
    }



    fun secondTerm(secondTerm: String?): UserTermPredicator {
        if (secondTerm.isNullOrEmpty()) return this
        if (secondTerm.contains(SEPARATOR)) {
            val condition = secondTerm.split(SEPARATOR)
                .filter { v -> v.isNotEmpty() && secondTerm.contains(v.single()) }
                .map { v -> v.single() }
            if (condition.isEmpty()) return this
            builder.and(table.firstTerm.`in`(condition))
        } else {
            if (AGREEMENT_YN.contains(secondTerm.first())) builder.and(table.secondTerm.eq(secondTerm.first()))
        }
        return this
    }

    fun value() = builder.value!!


}


