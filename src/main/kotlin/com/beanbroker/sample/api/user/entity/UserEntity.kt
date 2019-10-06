package com.beanbroker.sample.api.user.entity

import com.beanbroker.sample.global.entity.BaseEntity
import javax.persistence.*

@Entity(name = "users")
class UserEntity : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    var seq = 0

    @Column(name = "name")
    var name = ""

    @Column(name = "age")
    var age = 0

    @Column(name = "gender")
    var gender = ""

    @Column(name = "user_Id")
    var userId = ""


    @Column(name = "email")
    var email = ""

}