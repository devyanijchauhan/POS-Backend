package org.pgs.posback.controller;

import org.pgs.posback.model.TransactionModel;
import org.pgs.posback.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/all")
    public List<TransactionModel> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionModel> getTransactionById(@PathVariable("id") Long id) {
        Optional<TransactionModel> transactionData = transactionRepository.findById(id);
        return transactionData.map(transactionModel -> new ResponseEntity<>(transactionModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transactionModel) {
        try {
            transactionModel.setCreatedAt(new Date());
            transactionModel.setUpdatedAt(new Date());
            TransactionModel createdTransaction = transactionRepository.save(transactionModel);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{transactionid}")
    public ResponseEntity<TransactionModel> updateTransaction(@PathVariable("transactionid") Long transactionid, @RequestBody TransactionModel transactionModel) {
        Optional<TransactionModel> transactionData = transactionRepository.findById(transactionid);

        if (transactionData.isPresent()) {
            TransactionModel updatedTransaction = transactionData.get();
            updatedTransaction.setType(transactionModel.getType());
            updatedTransaction.setAmount(transactionModel.getAmount());
            updatedTransaction.setDateTime(transactionModel.getDateTime());
            updatedTransaction.setStore(transactionModel.getStore());
            updatedTransaction.setUpdatedAt(new Date());
            return new ResponseEntity<>(transactionRepository.save(updatedTransaction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idt}")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("idt") Long idt) {
        try {
            transactionRepository.deleteById(idt);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
