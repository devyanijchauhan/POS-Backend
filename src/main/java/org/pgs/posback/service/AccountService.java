package org.pgs.posback.service;

import org.pgs.posback.DTO.Account.AccountRequestDTO;
import org.pgs.posback.DTO.Account.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    List<AccountResponseDTO> getAllAccounts();

    AccountResponseDTO getAccountById(Long id);

    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);

    AccountResponseDTO updateAccount(Long accountId, AccountRequestDTO accountRequestDTO);

    void deleteAccount(Long accountId);
}
