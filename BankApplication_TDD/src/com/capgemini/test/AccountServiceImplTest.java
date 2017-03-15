package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitalAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;
public class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
		
	}
	
	/*
	 * create account
	 * 1.when the amount is less than 500 system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitalAmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientInitalAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitalAmountException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000));
	}

	
	/*
	 * deposit amount
	 * 1. when invalid account number is passed system should throw exception
	 * 2. when the valid info is passed amount should be deposited successfully
	 */
	
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberIsPassedSystemShouldThrowException() throws InvalidAccountNumberException
	{
		/*Account account = new Account();
		
		account.setAccountNumber(102);
		account.setAmount(1000);
		
		when(accountRepository.searchAccount(102)).thenReturn(account);*/
		accountService.depositAmount(102, 4000);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDepositedSuccessfully() throws InvalidAccountNumberException
	{
		Account account = new Account();
		
		account.setAccountNumber(102);
		account.setAmount(1000);
		
		when(accountRepository.searchAccount(102)).thenReturn(account);
		
		assertEquals(account.getAmount()+5000,accountService.depositAmount(102, 5000) );
		
	}
	
	/*
	 * withdraw amount
	 * 1.when invalid account number is passed system should throw exception
	 * 2.when the balance is not sufficient system should throw exception
	 * 3. when the valid info is passed amount should be withdraw
	 */
	
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberIsPassedInWithdrawSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		accountService.withdrawAmount(101, 5000);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
