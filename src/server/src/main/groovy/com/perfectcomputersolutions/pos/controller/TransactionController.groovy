package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//@RestController
//@RequestMapping("transaction")
class TransactionController extends CrudController<Transaction, Long> {

    @Autowired TransactionService service
}
