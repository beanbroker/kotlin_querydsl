package com.beanbroker.sample.config.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component("beanbrokerAuditor")
class BeanbrokerAuditor : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {

        return Optional.of("SampleServer")
    }

}
