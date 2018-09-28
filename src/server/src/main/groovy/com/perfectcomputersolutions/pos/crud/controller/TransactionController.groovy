package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.TransactionService
import com.perfectcomputersolutions.pos.model.Transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for API calls related to transactions.
 *
 * @see Transaction
 */
@RestController
@RequestMapping("/transaction")
class TransactionController extends CrudController<Transaction, String> {

//    private static final Logger Log = LoggerFactory.getLogger(TransactionController.class)

    @Autowired TransactionService service
}
