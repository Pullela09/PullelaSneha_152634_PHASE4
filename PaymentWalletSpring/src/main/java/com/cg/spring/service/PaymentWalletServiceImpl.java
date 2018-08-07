package com.cg.spring.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.beans.Customer;
import com.cg.spring.beans.Transaction;
import com.cg.spring.repo.PaymentWalletRepo;

@Service("paymentservice")
public class PaymentWalletServiceImpl implements PaymentWalletService {

	
	@Autowired
	private PaymentWalletRepo repo;
	
	@Override
	public void createAccount(Customer customer) {
	
		repo.save(customer);
	}

	@Override
	public Optional<Customer> showBalance(String moblineNo) {
		
		  return repo.findById(moblineNo);
	}

	@Override
	public Optional<Customer> findOne(String moblieNo) {
		
		
		return  repo.findById(moblieNo);
	}

	@Override
	public String fundTranfer(String sourceMobile, String targetMobile, BigDecimal amount) {
		String result=null;
		if(repo.findById(targetMobile).get()!=null) {
			
			withdraw(sourceMobile, amount);
				deposit(targetMobile, amount);
				BigDecimal source=repo.findById(sourceMobile).get().getWallet();
				result="Transferred succesfull and current balance is:" +source;
			}
		return result;
		}
		
	

	@Override
	public void deposit(String mobileNo, BigDecimal amount) {
	
	Optional<Customer> c= repo.findById(mobileNo);
		BigDecimal amt=c.get().getWallet().add(amount);
		c.get().setWallet(amt);
		Customer customer=c.get();
	Transaction transaction = new Transaction();
		    transaction.setType("Credited");
		    transaction.setAmount(amount);
		    transaction.setBalance(customer.getWallet());
		    java.util.Date today = new java.util.Date();
			transaction.setDateOfTrans( new java.sql.Timestamp(today.getTime()));
			transaction.setCustomer(customer);
			customer.addTransaction(transaction);
		repo.save(customer);
		
	}

	@Override
	public void withdraw(String mobileNo, BigDecimal amount) {
	

		Optional<Customer> c1= repo.findById(mobileNo);
		BigDecimal amt1=c1.get().getWallet().subtract(amount);
		c1.get().setWallet(amt1);
		Customer customer1=c1.get();
		Transaction transaction = new Transaction();
	    transaction.setType("Debited");
	    transaction.setAmount(amount);
	    transaction.setBalance(customer1.getWallet());
	    java.util.Date today = new java.util.Date();
		transaction.setDateOfTrans( new java.sql.Timestamp(today.getTime()));
		transaction.setCustomer(customer1);
		customer1.addTransaction(transaction);
		repo.save(customer1);
	
	}

	@Override
	public Set<Transaction> printTransactions(String mobileno) {
		Customer customer = repo.findById(mobileno).get();
		 
			return customer.getTransactions();
		
		//return null;
	} 
 
}
