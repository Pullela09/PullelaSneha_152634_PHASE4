package com.cg.spring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.beans.Customer;

@Repository("paymentrepo")
public interface PaymentWalletRepo extends CrudRepository<Customer, String>{

}
