package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.annotation.Legit
import com.perfectcomputersolutions.pos.annotation.NoNullArgs
import com.perfectcomputersolutions.pos.model.Customer
import com.perfectcomputersolutions.pos.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
@Legit
@NoNullArgs
class CustomerService extends CrudService<Customer, Long> {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class)

    @Autowired CustomerRepository repository

    def search(String param, int page, int size) {

        // TODO - If empty string??

        log.info("Retrieving customers that resemble: '${param}'")

        def pageable = new PageRequest(page, size)

        return repository.findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContainingOrEmailIgnoreCaseContaining(
                param,
                param,
                param,
                pageable
        )
    }
}
