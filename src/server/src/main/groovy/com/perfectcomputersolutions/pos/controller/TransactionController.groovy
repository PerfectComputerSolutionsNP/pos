package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.service.TransactionService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transaction")
@Api(value="transaction", description='Operations pertaining to transactions.')
class TransactionController extends UnprivilegedCrudController<Transaction, Long> {

    @Autowired TransactionService service
}
