package com.gt.app.rest;

import com.gt.app.commons.Response;
import com.gt.app.db.entities.Account;
import com.gt.app.request.NameRequest;
import com.gt.app.service.AccountService;
import com.gt.app.service.TransactionService;
import com.gt.app.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * TransactionController
 *
 * @author yousheng
 * @since 2018/4/24
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/get_transactions")
    public Response getTransactions(@RequestBody NameRequest nameRequest, HttpServletResponse response) {

        Account account = accountService.findByName(nameRequest.getEmail());

        if (account == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Responses.err("account not exists.");
        }
        return Responses.ok(transactionService.getTxByAccount(account.getId(), account.getName()));
    }
}
