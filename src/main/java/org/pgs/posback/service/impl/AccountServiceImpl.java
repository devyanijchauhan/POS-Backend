package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Account.AccountRequestDTO;
import org.pgs.posback.DTO.Account.AccountResponseDTO;
import org.pgs.posback.mapper.AccountMapper;
import org.pgs.posback.model.AccountModel;
import org.pgs.posback.repository.AccountRepository;
import org.pgs.posback.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<AccountModel> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::accountModelToAccountResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Optional<AccountModel> optionalAccount = accountRepository.findById(id);
        return optionalAccount.map(AccountMapper.INSTANCE::accountModelToAccountResponseDTO).orElse(null);
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        AccountModel account = AccountMapper.INSTANCE.accountRequestDTOToAccountModel(accountRequestDTO);
        AccountModel savedAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.accountModelToAccountResponseDTO(savedAccount);
    }

    @Override
    public AccountResponseDTO updateAccount(Long accountId, AccountRequestDTO accountRequestDTO) {
        Optional<AccountModel> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            AccountModel account = optionalAccount.get();
            account.setUsername(accountRequestDTO.getUsername());
            account.setPassword(accountRequestDTO.getPassword());
            account.setRole(accountRequestDTO.getRole());
            account.setUpdatedAt(accountRequestDTO.getUpdatedAt());
            AccountModel updatedAccount = accountRepository.save(account);
            return AccountMapper.INSTANCE.accountModelToAccountResponseDTO(updatedAccount);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
