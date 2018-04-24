package com.gt.app.service;

import com.gt.app.db.entities.Account;
import com.gt.app.db.entities.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public String transfer(String from, String to, double amount) {
        if (amount <= 0) {
            return "amount invalid";
        }

        Account account = accountRepo.findAccountByName(from);
        if (account == null) {
            return "account not found";
        }

        BigDecimal original = account.getBalance();
        if (original.compareTo(BigDecimal.valueOf(amount)) < 0) {
            return "balance is not enough.";
        }

        Account transferee = accountRepo.findAccountByName(to);
        if (transferee == null) {
            return "transferee not exists.";
        }

        accountRepo.updateAccountBalanceByIdAndBalance(amount * -1, account.getId(), account.getBalance());
        accountRepo.updateAccountBalanceByIdAndBalance(amount, transferee.getId(), transferee.getBalance());

        long now = Instant.now().toEpochMilli();

        transactionService.generateTx(account.getId(), transferee.getId(), amount, now);

        return null;
    }

    public Account findByName(String name) {
        return accountRepo.findAccountByName(name);
    }

    public Account findById(int id) {
        return accountRepo.findById(id).orElse(new Account().setName("ACCOUNT NOT EXISTS"));
    }
}
