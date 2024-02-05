package com.bytecoder.BankManagement.service;

import com.bytecoder.BankManagement.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAll();
    Account getAccountById(Long id);
    Account deposit(Long id , double amount);
    Account withdraw(Long id , double amount);
    void deleteAccount(Long id);
}
