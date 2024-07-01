package com.surajkhanna.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surajkhanna.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
