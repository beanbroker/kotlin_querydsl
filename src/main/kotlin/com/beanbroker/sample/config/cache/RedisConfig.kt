package com.beanbroker.sample.config.cache

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.CacheErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.nio.charset.StandardCharsets
import java.time.Duration


@Configuration
@EnableRedisRepositories
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    @Value("\${spring.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.redis.port}")
    private val redisPort: Int = 0

    @Value("\${spring.redis.password}")
    private val redisPassword: String? = null

    @Bean()
    fun lettuceConnectionFactory(): RedisConnectionFactory {


        return LettuceConnectionFactory(redisHost!!, redisPort)
    }

    @Bean("redisTemplateWithLettuce")
    fun redisTemplateWithLettuce(

    ): RedisTemplate<*, *> {

        val template = RedisTemplate<String, Any>()
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        template.setConnectionFactory(lettuceConnectionFactory())
        template.setEnableTransactionSupport(true)


        return template
    }


//    @Bean()
//    fun jedisConnectionFactory(): JedisConnectionFactory {
//
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost!!, redisPort)
//        redisStandaloneConfiguration.setPassword(redisPassword)
//
//        return JedisConnectionFactory(redisStandaloneConfiguration)
//    }
//
//
//    @Bean("redisTemplateWithJedis")
//    fun redisTemplateWithJedis(
//
//    ): RedisTemplate<*, *> {
//
//        val template = RedisTemplate<String, Any>()
//        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()
//        template.hashKeySerializer = StringRedisSerializer()
//        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
//
//        template.setConnectionFactory(jedisConnectionFactory())
//        template.setEnableTransactionSupport(true)
//
//
//        return template
//    }



}