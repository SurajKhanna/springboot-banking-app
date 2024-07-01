package com.surajkhanna.banking.service;

import java.util.List;

import com.surajkhanna.banking.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount (AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> findAllAccounts();
	
	void deleteAccount(Long id);

}
