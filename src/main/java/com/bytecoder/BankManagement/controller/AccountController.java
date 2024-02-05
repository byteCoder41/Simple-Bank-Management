package com.bytecoder.BankManagement.controller;

import com.bytecoder.BankManagement.entity.Account;
import com.bytecoder.BankManagement.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // REST API for creating new account
    @PostMapping
    public Account addAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    // REST API for deleting account by id
    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }

    // REST API for getting a specific account by id
    @GetMapping("{id}")
    public Account getById(@PathVariable("id")  Long id){
        return accountService.getAccountById(id);
    }

    // REST API for depositing amount in a specific account on the basis of id
    @PutMapping("{id}/deposit")
    public Account deposit (@PathVariable long id,
                            @RequestBody Map<String, Double> request
                            )
    {
        Double amount = request.get("amount");
        return accountService.deposit(id,amount);
    }

    // REST API for withdraw amount from a specific account on the basis of id
    @PutMapping("{id}/withdraw")
    public Account withdraw (@PathVariable long id,
                            @RequestBody Map<String, Double> request
    )
    {
        Double amount = request.get("amount");
        return accountService.withdraw(id,amount);
    }

    // REST API for getting all accounts
    @GetMapping
    List<Account> getAllAccounts(){
        return  accountService.getAll();
    }

}
