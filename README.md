kotlin_querydsl




## Querydsl with Kotlin 

코틀린과 Querydsl 샘플 코드가.... 구글에 찾아도 없다.. 누군가는 간절하게 찾는 그런 정보를 남겨보자!

[Querydsl이란?](http://www.querydsl.com/static/querydsl/4.0.1/reference/ko-KR/html_single/)

공식홈페이지 참고


## 샘플코드

[깃헙 샘플 코드 링크](https://github.com/beanbroker/kotlin_querydsl)

## DB 셋팅

docker에 mysql 컨테이너를 띄워서 진행!

```sql
CREATE TABLE `users` (
  `seq` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `age` int(10) unsigned NOT NULL,
  `gender` varchar(1) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8
```

createdBy와 updatedBy 컬럼은 Audit을 활용할 예정

## gradle 디펜던시 추가


중요 부분은 아래와 같다
```d


idea {
	module {
		def kaptMain = file('build/generated/source/kapt/main')
		sourceDirs += kaptMain
		generatedSourceDirs += kaptMain
	}
}


compile("com.querydsl:querydsl-jpa:${queryDslVersion}")
kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jpa")
```

전용 도메인 클래스는 /build/generated/source/kapt/main 디렉토리 이하에 패키지 단위로 자동 생성된다. sourceSets 옵션을 수정하여 생성 위치를 변경할 수 있다.

## application.properties

디비 환경설정 셋팅, yml로 진행해도 무관하다

```yml
server.port=7070
spring.datasource.url=jdbc:mysql://localhost:3306/study
spring.datasource.username=root
spring.datasource.password=password
```


## entity 패키지

AuditingEntityListener는 추후설명!!

```java

@Entity(name = "users")
@EntityListeners(AuditingEntityListener::class)
class UserEntity{

    @Id
    @Column(name = "seq")
    var seq = 0

    @Column(name = "name")
    var name = ""

    @Column(name = "age")
    var age = 0

    @Column(name = "gender")
    var gender = ""


    @CreatedDate
    @Column(name = "created_at" , nullable = false, updatable = false,  columnDefinition = "DATE")
    var createdAt  : LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Column(name = "created_by")
    var createdBy = ""

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATE")
    var updatedAt  : LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    @Column(name = "updated_by")
    var updateBy = ""


}
```

## repository package

get은 왠지 느낌상 하나만 가져오는 느낌? 이라서 단건을 조회시 많이 사용한다..
find는 몬지 다찾아줄것 같은 느낌이라...... 개인적인 의견일뿐

queryDsl이 매력적인 이유는 type safe할뿐더러 쿼리를 치는 느낌의 어플리케이션 코드라 직관적이고 이해하기 쉽다.


```java

interface UserRepositoryCustom{

    fun getBySeq(seq : Int): UserEntity?
    fun getByName(name : String): UserEntity?
    fun findByGender(gender : String): List<UserEntity>?

}

interface UserRepository : JpaRepository<UserEntity, Int>, UserRepositoryCustom

class UserRepositoryImpl :
    QuerydslRepositorySupport(UserEntity::class.java),UserRepositoryCustom{
   

    override fun getBySeq(seq: Int): UserEntity? {
        val table = QUserEntity.userEntity
        return from(table)
            .where(table.seq.eq(seq))
            .fetchOne()

    }

    override fun getByName(name : String): UserEntity? {
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
}

```


## service


유저 create, get 서비스


```java 
@Service
class UserCreateService(
    private val userRepository: UserRepository

){

    fun saveUser(userEntity: UserEntity){
        userRepository.save(userEntity)
    }

}

-------------------


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

}

```



## 실제 작성 코드

물론 rest하게 진행하면 좋겟지만 이글의 목적은 kotling, querydsl, spring 예제일뿐이다~!



```java

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


		val userEntity = userGetService.getUserBySeq(1)

		val a = "test"

	}

}



```

1. 박기진, 20살, 남성 저장
2. 데이터 확인 정상
3. 일단 처음생성했으니가 당연히 오토인크리먼트되는 seq로 조회
4. 정상작동확인.

어플리케이션 프로퍼티 설정에 jpa query show 설정을 해놓으면 더 보기 쉽다!


## 주의사항

만약 api 서버를 만들때 entity와 domain 객체를 별도로!! 꼬옥!! 별도록 생성해서 진행하자 왜냐? "영속성" 


아... 공부할게 너무많은데... 다음에... audit관련...을 2장으로.. 코루틴은... 나중에..


------------------------


## Kotlin, Querydsl 2장 (JPA Auditing)

Audit의 사전적 의미 감사(고마움을..표시하는 그런뜻이..아닌...)

1장에서 유저 Entity에 @CreatedBy, @LastModifiedBy를 이어서 카즈아!!

말그대로 누구에 의해 만들어졌는지, 누구에 의해 수정되었는지를 뜻한다. 

* Declares a field as the one representing the principal that created the entity containing the field.
* Declares a field as the one representing the date the entity containing the field was recently modified.


어떻게 사용하면 좋을가?

예를들어 하나의 DB를 바라보며 서비스 어플리케이션, 배치 어플리케이션, 관리용 어플리케이션이 떠 있을 경우 DB의 변경을 어디서 했는지 로그를 보지 않고서는 알수가 없다.(물론 디비로그 설정이나 다른 방법으로도 풀수 있다! aws에서 버튼 몇번 누르면 되는 충격적인...)

만약 Audit을 사용하게 된다면 어디서 만들어졌는지 어디서 변경되었는지 쉽게 찾을수 있다. 또한 최대한 DB는 DB담당자가 아닌 개발자들이 절대 직접 핸들링하면 안되는 곳이라 생각된다.


### 샘플소스

> UserEntity 

```java

@Entity(name = "users")
@EntityListeners(AuditingEntityListener::class)
class UserEntity{

    @Id
    @Column(name = "seq")
    var seq = 0

    @Column(name = "name")
    var name = ""

    @Column(name = "age")
    var age = 0

    @Column(name = "gender")
    var gender = ""


    @CreatedDate
    @Column(name = "created_at" , nullable = false, updatable = false,  columnDefinition = "DATE")
    var createdAt  : LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Column(name = "created_by")
    var createdBy = ""

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATE")
    var updatedAt  : LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    @Column(name = "updated_by")
    var updateBy = ""


}
```

> AuditConfig 

```java
@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
class AuditConfig{

}
```

> BeanbrokerAuditor

```java
@Component("beanbrokerAuditor")
class BeanbrokerAuditor : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {

        return Optional.of("SampleServer")
    }

}
```

위와 같이 설정을 하게되면 생성 또는 수정시 create_by, updated_by에 SampleServer가 들어간다!. 테스트는 생성, 업데이트, 삭제시 동일하게 작동한다. 테스트 코드를 디버깅하면서 db를 확인하게 되면 파악할수 있다!




3장에서는 조인!!! queryDsl조인을 알아보자



