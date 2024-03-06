package org.pgs.posback.controller;

import org.pgs.posback.DTO.Account.AccountRequestDTO;
import org.pgs.posback.DTO.Account.AccountResponseDTO;
import org.pgs.posback.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO account = accountService.getAccountById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO createdAccount = accountService.createAccount(accountRequestDTO);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long accountId, @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO updatedAccount = accountService.updateAccount(accountId, accountRequestDTO);
        return updatedAccount != null ? ResponseEntity.ok(updatedAccount) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Ids}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long Ids) {
        accountService.deleteAccount(Ids);
        return ResponseEntity.noContent().build();
    }
}
