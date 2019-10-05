package com.beanbroker.sample.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class SampleTokenService {


    companion object {

        val ISSURE = "BEANBROKER"
        val SCRET = "1234"

    }

    fun createToken(id: Long, authType: String): BeanbrokerToken {

        //60 * 60 * 24
        val now = LocalDateTime.now()
        val expiredAt = now.plusSeconds(86400)

        return BeanbrokerToken().apply {
            this.expireDate = expiredAt
            this.token = JWT.create()
                    .withClaim("id", id)
                    .withClaim("authType", authType)
                    .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                    .withIssuer(ISSURE)
                    .withExpiresAt(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(Algorithm.HMAC256(SCRET))
        }

    }

    fun decodeToken(token: String) {



        val jwt = JWT.require(Algorithm.HMAC256(SCRET))
                .withIssuer(ISSURE)
                .build()
                .verify(token)

    }


}
