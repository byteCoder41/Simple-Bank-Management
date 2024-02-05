package com.bytecoder.BankManagement.service.impl;

import com.bytecoder.BankManagement.entity.Account;
import com.bytecoder.BankManagement.repository.AccountRepository;
import com.bytecoder.BankManagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public Account createAccount(Account account) {
        Account account1 = new Account();
        account1.setAccountHolderName(account.getAccountHolderName());
        account1.setBalance(account.getBalance());
        accountRepository.save(account1);
        return account1;
    }

    @Override
    public List<Account> getAll() {
      return  accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("Resource not found"));
    }

    @Override
    public Account deposit(Long id, double amount) {
       Account account = accountRepository
               .findById(id)
               .orElseThrow(
                       ()->new RuntimeException("Resource not found")
               );
       double bl = account.getBalance()+amount;
       account.setBalance(bl);
       return accountRepository.save(account);
    }

    @Override
    public Account withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()->new RuntimeException("Resource not found")
                );
        if (amount>account.getBalance()){
            throw new RuntimeException("Low Balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        accountRepository.save(account);
        return account;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(
                        ()->new RuntimeException("Resource not found")
                );
        accountRepository.delete(account);
    }
}
