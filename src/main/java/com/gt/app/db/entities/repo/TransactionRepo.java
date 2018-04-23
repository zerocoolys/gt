package com.gt.app.db.entities.repo;

import com.gt.app.db.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TransactionRepo
 *
 * @author yousheng
 * @since 2018/4/23
 */
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
