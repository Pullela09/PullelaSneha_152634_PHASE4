package com.cg.spring.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.spring.beans.Customer;
import com.cg.spring.beans.Transaction;


public interface PaymentWalletService {
	public void createAccount(Customer customer);
	public Optional<Customer> showBalance(String moblineNo);
	public Optional<Customer> findOne(String moblieNo);
	public String fundTranfer(String sourceMobile, String targetMobile, BigDecimal amount);
	public void deposit(String mobileNo,BigDecimal amount);
	public void withdraw(String mobileNo,BigDecimal amount);
	public Set<Transaction> printTransactions(String mobileno);

}
