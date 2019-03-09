package com.beanbroker.sample.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id

@Entity(name = "user_terms")
@EntityListeners(AuditingEntityListener::class)
class UserTermEntity{

    @Id
    @Column(name = "seq")
    var seq = 0

    @Column(name = "first_term")
    var firstTerm : Char = 'N'

    @Column(name = "second_term")
    var secondTerm : Char = 'N'

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