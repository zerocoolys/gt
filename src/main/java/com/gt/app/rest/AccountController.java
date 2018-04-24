package com.gt.app.rest;

import com.google.common.base.Strings;
import com.gt.app.commons.AccountVO;
import com.gt.app.commons.Response;
import com.gt.app.db.entities.Account;
import com.gt.app.db.entities.repo.AccountRepo;
import com.gt.app.request.NameRequest;
import com.gt.app.request.TransferRequest;
import com.gt.app.service.AccountService;
import com.gt.app.utils.Responses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * AccountController
 *
 * @author yousheng
 * @since 2018/4/23
 */
@RestController
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accountService;

    /**
     * search user account info
     *
     * @param nameRequest
     * @return
     */
    @PostMapping("/get_account")
    public Response getAccount(@RequestBody NameRequest nameRequest, HttpServletResponse response) {
        Account account = accountRepo.findAccountByName(nameRequest.getEmail());

        if (account == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return Responses.err("account name not exists.");
        } else {
            return Responses.ok(account);
        }
    }

    private final BigDecimal BONUS = BigDecimal.valueOf(10000.0);

    @PostMapping("/accounts")
    public Response register(@RequestBody NameRequest nameRequest, HttpServletResponse response) {

        String name = nameRequest.getEmail();
        Account account = new Account().setName(name).setBalance(BONUS);

        account = accountRepo.save(account);

        if (account.getId() == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return Responses.err("save failed.");
        } else {
            AccountVO accountVO = new AccountVO();
            BeanUtils.copyProperties(account, accountVO);
            return Responses.ok(accountVO);
        }
    }

    @PostMapping("/transfer")
    public Response transfer(@RequestBody TransferRequest request, HttpServletResponse response) {

        String from = request.getEmail();
        String to = request.getTransferee();
        double amount = request.getAmount();

        String msg = accountService.transfer(from, to, amount);

        if (Strings.isNullOrEmpty(msg)) {
            return Responses.ok();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Responses.err(msg);
        }
    }
}
