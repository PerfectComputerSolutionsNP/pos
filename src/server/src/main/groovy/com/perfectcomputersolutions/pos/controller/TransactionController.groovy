package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.service.TransactionService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired

//@RestController
//@RequestMapping("transaction")
//@Api(value="category", description='Operations pertaining to transactions.')
class TransactionController extends UnprivilegedCrudController<Transaction, Long> {

    @Autowired TransactionService service
}
