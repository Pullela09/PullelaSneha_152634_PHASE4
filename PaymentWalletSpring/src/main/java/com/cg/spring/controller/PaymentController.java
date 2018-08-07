package com.cg.spring.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.beans.Customer;
import com.cg.spring.beans.Transaction;
import com.cg.spring.service.PaymentWalletService;

@RestController
public class PaymentController {
	@Autowired
	private PaymentWalletService service;
	
	
	@RequestMapping(value="/customer",method=RequestMethod.POST)
	public void createAccount(@RequestBody Customer customer) {
		
		service.createAccount(customer);
	}
	
	@RequestMapping("/customer/{moblineNo}")
	public Optional<Customer> showBalance( @PathVariable String moblineNo) {
		 
		return service.showBalance(moblineNo);
		
	}
	@RequestMapping("/customers/{mobileNo}")
	public Optional<Customer> findOne( @PathVariable String mobileNo) {
	return service.findOne(mobileNo);
		
	}
	
	@RequestMapping(value="/customer/{sourceMobile}/{targetMobile}/{amount}", method=RequestMethod.POST)
	public String fundTranfer(@PathVariable String sourceMobile,@PathVariable String targetMobile,@PathVariable BigDecimal amount) {
		 return service.fundTranfer( sourceMobile, targetMobile, amount);
		
	}
	@RequestMapping(value="/customer/{mobileNo}/{amount}", method=RequestMethod.PUT)
	public void deposit(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		 service.deposit(mobileNo, amount);
		
	}
	
	@RequestMapping(value="/customers/{mobileNo}/{amount}", method=RequestMethod.PUT)
	public void withdraw(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		
		 service.withdraw(mobileNo, amount);
	}
	
	@RequestMapping("/printtransactions/{mobileno}")
	public LinkedHashSet<Transaction> printTransactions(@PathVariable String mobileno) {
		Set<Transaction> mytransactions=service.printTransactions(mobileno);
		List<Transaction> transac=new  LinkedList<Transaction>(mytransactions);
		LinkedHashSet<Transaction> result = new LinkedHashSet<>();
		Collections.sort(transac,  new Comparator<Transaction>() {
			   

			public int compare(Transaction o1, Transaction o2) {
				// TODO Auto-generated method stub
				return o1.getDateOfTrans().compareTo(o2.getDateOfTrans());
			}
		});
		Iterator<Transaction> iterator = transac.iterator();
		while (iterator.hasNext()) {
			result.add( iterator.next());
			
		}
		return result;
	} 

}







