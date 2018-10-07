package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Customer
import com.perfectcomputersolutions.pos.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService extends CrudService<Customer, Long> {

    @Autowired CustomerRepository repository
}
