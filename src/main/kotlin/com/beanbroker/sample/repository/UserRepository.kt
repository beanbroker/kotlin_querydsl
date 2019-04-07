package com.beanbroker.sample.repository

import com.beanbroker.sample.entity.QUserEntity
import com.beanbroker.sample.entity.QUserTermEntity
import com.beanbroker.sample.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport


interface UserRepositoryCustom {

    fun getBySeq(seq: Int): UserEntity?
    fun getByName(name: String): UserEntity?
    fun findByGender(gender: String): List<UserEntity>?

    fun getUserEmailList(): MutableList<String>?

}

interface UserRepository : JpaRepository<UserEntity, Int>, UserRepositoryCustom

class UserRepositoryImpl :
    QuerydslRepositorySupport(UserEntity::class.java), UserRepositoryCustom {


    override fun getBySeq(seq: Int): UserEntity? {
        val table = QUserEntity.userEntity
        return from(table)
            .where(table.seq.eq(seq))
            .fetchOne()

    }

    override fun getByName(name: String): UserEntity? {
        val table = QUserEntity.userEntity
        return from(table)
            .where(table.name.eq(name))
            .fetchOne()

    }


    override fun findByGender(gender: String): List<UserEntity>? {
        val table = QUserEntity.userEntity
        return from(table)
            .where(table.gender.eq(gender))
            .fetch()
    }

    override fun getUserEmailList(): MutableList<String>? {
        val table = QUserEntity.userEntity
        val termTable = QUserTermEntity.userTermEntity


        return from(table)
            .select(table.email)
            .innerJoin(termTable).on(termTable.userId.eq(table.userId)
            , termTable.firstTerm.eq('Y'))
            .fetch()


    }
}



