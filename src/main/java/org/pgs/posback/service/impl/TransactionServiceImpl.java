package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Transaction.TransactionRequestDTO;
import org.pgs.posback.DTO.Transaction.TransactionResponseDTO;
import org.pgs.posback.mapper.TransactionMapper;
import org.pgs.posback.model.TransactionModel;
import org.pgs.posback.repository.TransactionRepository;
import org.pgs.posback.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        List<TransactionModel> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper.INSTANCE::transactionModelToTransactionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.map(TransactionMapper.INSTANCE::transactionModelToTransactionResponseDTO).orElse(null);
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        TransactionModel transaction = TransactionMapper.INSTANCE.transactionRequestDTOToTransactionModel(transactionRequestDTO);
        transaction.setCreatedAt(new Date());
        transaction.setUpdatedAt(new Date());
        TransactionModel savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.INSTANCE.transactionModelToTransactionResponseDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long transactionId, TransactionRequestDTO transactionRequestDTO) {
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isPresent()) {
            TransactionModel transaction = optionalTransaction.get();
            transaction.setType(transactionRequestDTO.getType());
            transaction.setAmount(transactionRequestDTO.getAmount());
            transaction.setDateTime(transactionRequestDTO.getDateTime());
            transaction.setStore(transactionRequestDTO.getStore());
            transaction.setUpdatedAt(new Date());
            TransactionModel updatedTransaction = transactionRepository.save(transaction);
            return TransactionMapper.INSTANCE.transactionModelToTransactionResponseDTO(updatedTransaction);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
