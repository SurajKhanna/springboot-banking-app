package com.surajkhanna.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //To Generate Getters setters and ToString directly.
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private Long id;
	private String accountHolderName;
	private double balance;
}
