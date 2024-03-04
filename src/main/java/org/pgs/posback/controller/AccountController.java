package org.pgs.posback.controller;

import org.pgs.posback.model.AccountModel;
import org.pgs.posback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/all")
    public List<AccountModel> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable("id") Long id) {
        Optional<AccountModel> accountData = accountRepository.findById(id);
        return accountData.map(accountModel -> new ResponseEntity<>(accountModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel accountModel) {
        try {
            AccountModel createdAccount = accountRepository.save(accountModel);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{accountid}")
    public ResponseEntity<AccountModel> updateAccount(@PathVariable("accountid") Long accountid, @RequestBody AccountModel accountModel) {
        Optional<AccountModel> accountData = accountRepository.findById(accountid);

        if (accountData.isPresent()) {
            AccountModel updatedAccount = accountData.get();
            updatedAccount.setUsername(accountModel.getUsername());
            updatedAccount.setPassword(accountModel.getPassword());
            updatedAccount.setRole(accountModel.getRole());
            updatedAccount.setCreatedAt(accountModel.getCreatedAt());
            updatedAccount.setUpdatedAt(accountModel.getUpdatedAt());
            return new ResponseEntity<>(accountRepository.save(updatedAccount), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ida}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("ida") Long ida) {
        try {
            accountRepository.deleteById(ida);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}