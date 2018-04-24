package com.gt.app.service;

import com.gt.app.commons.TransactionVO;
import com.gt.app.db.entities.Account;
import com.gt.app.db.entities.Transaction;
import com.gt.app.db.entities.repo.TransactionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TransactionService
 *
 * @author yousheng
 * @since 2018/4/24
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountService accountService;

    /**
     *
     * @param uid transation uid
     * @param from
     * @param to
     * @param type
     * @param amount
     * @param time
     */
    public void createTx(int uid, int from, int to, String type, double amount, long time) {
        Transaction tx = new Transaction();
        tx.setUid(uid);
        tx.setFrom(from);
        tx.setTo(to);
        tx.setType(type);
        tx.setAmount(BigDecimal.valueOf(amount));
        tx.setDatetime(time);

        transactionRepo.save(tx);
    }

    public List<TransactionVO> getTxByAccount(int accountId, String name) {
        List<Transaction> transactions = transactionRepo.getAllByUid(accountId);

        if (transactions.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        final Map<Integer, String> idNamePair = new HashMap<>();

        idNamePair.put(accountId, name);
        return transactions.stream().map(transaction -> {
            TransactionVO transactionVO = new TransactionVO();
            transactionVO.setFrom(computeName(idNamePair, transaction.getFrom()))
                    .setTo(computeName(idNamePair, transaction.getTo()))
                    .setAmount(transaction.getAmount().doubleValue())
                    .setType(transaction.getType());

            transactionVO.setDatetime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date(transaction.getDatetime())));
            return transactionVO;
        }).collect(Collectors.toList());
    }

    protected String computeName(final Map<Integer, String> idNamePair, int id) {
        return idNamePair.computeIfAbsent(id, integer -> {
            Account from = accountService.findById(id);
            return from.getName();
        });
    }

    /**
     * create transaction records for both sides
     *
     * @param from
     * @param to
     * @param amount
     * @param now
     */
    public void generateTx(Integer from, Integer to, double amount, long now) {
        createTx(from, from, to, "transfer", amount, now);
        createTx(to, to, from, "receive", amount, now);

    }
}
