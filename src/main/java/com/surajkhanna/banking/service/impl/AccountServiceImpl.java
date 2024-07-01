package com.surajkhanna.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.surajkhanna.banking.dto.AccountDto;
import com.surajkhanna.banking.entity.Account;
import com.surajkhanna.banking.mapper.AccountMapper;
import com.surajkhanna.banking.repository.AccountRepository;
import com.surajkhanna.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;	

//	We need to write @Autowired Annotation here. But from SpringBoot 4.3 onwards, 
//	if a class has a single variable constructor, one can ignore writing that
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);	
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		//Used Lambda Expression to Throw and this is an Optional Class
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account Does not Exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account Does not Exist"));
		
		double totalBalance = account.getBalance() + amount;
		
		account.setBalance(totalBalance);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account Does not Exist"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double totalBalance = account.getBalance() - amount;
		
		account.setBalance(totalBalance);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);

	}

	@Override
	public List<AccountDto> findAllAccounts() {
		List<Account> getAllAccounts = accountRepository.findAll();
		return getAllAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account Does not Exist"));
		
		accountRepository.deleteById(id);
		
	}
	
	

}
