package com.beanbroker.sample


import com.beanbroker.sample.api.user.domain.User
import com.beanbroker.sample.api.user.entity.UserEntity
import com.beanbroker.sample.api.user.service.UserCreateService
import com.beanbroker.sample.api.user.service.UserGetService
import com.beanbroker.sample.api.user.service.UserTermService
import com.beanbroker.sample.api.user.service.UserUpdateService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SampleApplicationTests {


    @Autowired
    private lateinit var userGetService: UserGetService

    @Autowired
    private lateinit var userCreateService: UserCreateService

    @Autowired
    private lateinit var userUpdateService: UserUpdateService

    @Autowired
    private lateinit var userTermService: UserTermService


    @Test
    fun createUser(){

        userCreateService.saveUser(userEntity = UserEntity().apply {

            this.name = "박기진"
            this.age = 20
            this.gender = "M"
            this.userId = "tes"
            this.email = "te@saf.com"
        })


    }

    @Test
    fun queryDslTest() {


        userCreateService.saveUser(userEntity = UserEntity().apply {

            this.name = "박기진"
            this.age = 20
            this.gender = "M"
            this.userId = "tes"
            this.email = "te@saf.com"
        })


        val userEntity = userGetService.getUserBySeq(42)



        println("-------jpa geton--------")
        userGetService.getUserByOriginJpa(42)

        println("-------querydsl --------")
        userGetService.getUserBySeq(42)

    }


    @Test
    fun auditTest() {


        userUpdateService.updateUser(

                userGetService.getUserBySeq(42)?.apply {

                    age = 100
                }!!


        )

    }


    @Test
    fun termTest1() {


        var test = userTermService.getUserTermByPredicator(
                seq = 1,
                firstTerm = "N",
                secondTerm = "Y"
        )


        assertNotNull("데이터가 있는지?", test)
        assertEquals("첫번째 약관이 맞는지?", 'N', test!!.firstTerm)
        assertEquals("두번째 약관이 맞는지?", 'Y', test.secondTerm)
        println(test.toString())

    }


    @Test
    fun abd() {

        // For loop
        for (i in 0 until 10 step 3) {
            if (i == 6) continue
            println(i)
        }


        (1..10).forEach {
            if (it == 6) return@forEach
            println(it)
        }

    }


    @Test
    fun first() {

        val users = listOf<User>(

                User("pkj", 30),
                User("beanbroker", 40),
                User("A", 32),
                User("B", 27),
                User("C", 44),
                User("D", 30)

        )

        val listOfOverThirtyFive = users.filter { it.age > 35 }
        println(listOfOverThirtyFive.toString())


        val mapOfOverThirtyFive = users.filter { it.age > 35 }.map { it.name }

        println(mapOfOverThirtyFive.toString())


//        val isOlderThan50 = users.all { it.age > 50 }
//
//        println(isOlderThan50)
//
//        val isYoungerThan50 = users.all { it.age <40 }
//        println(isYoungerThan50)
//
//        val isAnyOneOlderThan50 = users.any { it.age > 50 }
//
//        println(isAnyOneOlderThan50)
//
//        val isAnyOnYoungerThan50 = users.any { it.age <40 }
//        println(isAnyOnYoungerThan50)


//        val countWhoIsYoungerThan35 = users.count{it.age < 35}
//        println(countWhoIsYoungerThan35)

//        val someoneWhoseAgeis32 = users.find { it.age == 32 }
//
//        println(someoneWhoseAgeis32.toString())
//
//
//        val someoneWhoseAgeis26 = users.find { it.age == 26 }
//
//
//        println(someoneWhoseAgeis26.toString())


        val userGroup = users.groupBy { it.age }.toList()

        println(userGroup.toString())


    }


    @Test
    fun joinTestForGetListOfEmail() {

//
//        var i = 100
//
//        for (x in  0..10){
//
//
//            UserEntity().apply {
//                this.userId = UUID.randomUUID().toString()
//                this.name = "pkj" + i.toString()
//                this.age = 25
//                this.email = this.userId + "@gmail.com"
//                this.gender = "M"
//
//
//            }.run {
//                var  b = userCreateService.saveUser(this)
//
//                userTermService.save( b.userId, 'Y', 'N')
//            }
//
//            userCreateService.saveUser(UserEntity().apply {
//                this.userId = UUID.randomUUID().toString()
//                this.name = "pkj" + i.toString()
//                this.age = 25
//                this.email = this.userId + "@gmail.com"
//                this.gender = "M"
//
//
//            })
//
//
//            i++
//
//        }

        val userEmailList = userGetService.getUserEmailList()

        println(userEmailList.toString())


    }

}

