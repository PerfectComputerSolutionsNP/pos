package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired

//@RestController
//@RequestMapping("transaction")
class TransactionController extends UnprivilegedCrudController<Transaction, Long> {

    @Autowired TransactionService service
}
