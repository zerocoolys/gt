package com.gt.app.db.entities.repo;

import com.gt.app.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * AccountRepo
 *
 * @author yousheng
 * @since 2018/4/23
 */
public interface AccountRepo extends JpaRepository<Account, Integer> {

    /**
     * search by account name
     *
     * @param name
     * @return
     */
    Account findAccountByName(String name);


    /**
     * update account balance
     * @param amount
     * @param id
     * @param balance the last balance read from db
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE account SET balance = balance +:amount WHERE id = :id AND balance = :balance")
    public void updateAccountBalanceByIdAndBalance(@Param("amount") double amount,
                                                   @Param("id") int id,
                                                   @Param("balance") BigDecimal balance);

}
