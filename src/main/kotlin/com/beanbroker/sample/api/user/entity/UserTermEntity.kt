package com.beanbroker.sample.api.user.entity

import com.beanbroker.sample.global.entity.BaseEntity
import javax.persistence.*

@Entity(name = "user_terms")
class UserTermEntity : BaseEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    var seq = 0

    @Column(name = "user_Id")
    var userId = ""

    @Column(name = "first_term")
    var firstTerm : Char = 'N'

    @Column(name = "second_term")
    var secondTerm : Char = 'N'

}