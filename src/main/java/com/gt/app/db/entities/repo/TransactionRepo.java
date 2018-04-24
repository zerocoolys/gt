package com.gt.app.db.entities.repo;

import com.gt.app.db.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TransactionRepo
 *
 * @author yousheng
 * @since 2018/4/23
 */
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    List<Transaction> getAllByFromOrTo(int from, int to);

    List<Transaction> getAllByUid(int accountId);
}
