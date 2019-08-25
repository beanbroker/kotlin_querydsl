package com.beanbroker.sample.api.user.entity

import com.beanbroker.sample.global.entity.BaseEntity
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
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