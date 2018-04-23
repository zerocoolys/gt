package com.gt.app.db.entities.repo;

import com.gt.app.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AccountRepo
 *
 * @author yousheng
 * @since 2018/4/23
 */
public interface AccountRepo extends JpaRepository<Account, Integer> {
}
