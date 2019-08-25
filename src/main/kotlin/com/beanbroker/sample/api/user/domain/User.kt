package com.beanbroker.sample.api.user.domain

data class User(

    var name : String = "",
    var age : Int = 0



) {
    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }
}