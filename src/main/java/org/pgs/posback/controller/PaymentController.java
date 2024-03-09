package org.pgs.posback.controller;

import org.pgs.posback.DTO.Payment.PaymentRequestDTO;
import org.pgs.posback.DTO.Payment.PaymentResponseDTO;
import org.pgs.posback.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable("id") Long id) {
        PaymentResponseDTO payment = paymentService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO createdPayment = paymentService.createPayment(paymentRequestDTO);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO updatedPayment = paymentService.updatePayment(paymentId, paymentRequestDTO);
        return updatedPayment != null ? ResponseEntity.ok(updatedPayment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{IdP}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long IdP) {
        paymentService.deletePayment(IdP);
        return ResponseEntity.noContent().build();
    }
}