package com.cg.spring.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
	@Table(name="cust_details")
	public class Customer implements Serializable {
		private static final long serialVersionUID = 1L;
	
		
		private String name;
	@Id
	
		private String mobileNo;
	
	@Embedded
		private Wallet wallet;
		public Customer() {
			wallet =  new Wallet();
		}
		
		@OneToMany(targetEntity = Transaction.class, mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private Set<Transaction> transactions = new HashSet<Transaction>(); 
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public BigDecimal getWallet() {
			return wallet.getBalance();
		}
		
		public void setWallet(BigDecimal amount) {
			wallet.setBalance(amount);
		}

		
		
		public Set<Transaction> getTransactions() {
			return transactions;
		}

		public void setTransactions(HashSet<Transaction> employees) {
			this.transactions = employees;
		}

		public void addTransaction(Transaction transaction) {
		
		transaction.setCustomer(this); // this will avoid nested cascade
			this.getTransactions().add(transaction);
		} 
		
		@Override
		public String toString() {
			return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
		}

		
		
		
	

}
