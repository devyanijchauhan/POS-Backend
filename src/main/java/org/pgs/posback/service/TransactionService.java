package org.pgs.posback.service;

import org.pgs.posback.DTO.Transaction.TransactionRequestDTO;
import org.pgs.posback.DTO.Transaction.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransactions();

    TransactionResponseDTO getTransactionById(Long id);

    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);

    TransactionResponseDTO updateTransaction(Long transactionId, TransactionRequestDTO transactionRequestDTO);

    void deleteTransaction(Long transactionId);
}
