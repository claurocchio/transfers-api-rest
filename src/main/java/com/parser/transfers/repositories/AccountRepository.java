package com.parser.transfers.repositories;

import com.parser.transfers.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}

