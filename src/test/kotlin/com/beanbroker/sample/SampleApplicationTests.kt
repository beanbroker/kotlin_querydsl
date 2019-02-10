package com.beanbroker.sample


import com.beanbroker.sample.entity.UserEntity
import com.beanbroker.sample.service.UserCreateService
import com.beanbroker.sample.service.UserGetService
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


	@Test
	fun queryDslTest() {



		userCreateService.saveUser(userEntity = UserEntity().apply {

			this.name ="박기진"
			this.age = 20
			this.gender = "M"
		})


		val userEntity = userGetService.getUserBySeq(42)



		println("-------jpa geton--------")
		userGetService.getUserByOriginJpa(42)

		println("-------querydsl --------")
		 userGetService.getUserBySeq(42)

	}

}

