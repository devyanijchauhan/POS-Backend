package org.pgs.posback.controller;

import org.pgs.posback.model.PaymentModel;
import org.pgs.posback.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/all")
    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable("id") Long id) {
        Optional<PaymentModel> paymentData = paymentRepository.findById(id);
        return paymentData.map(paymentModel -> new ResponseEntity<>(paymentModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PaymentModel> createPayment(@RequestBody PaymentModel paymentModel) {
        try {
            paymentModel.setCreatedAt(new Date());
            paymentModel.setUpdatedAt(new Date());
            PaymentModel createdPayment = paymentRepository.save(paymentModel);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{paymentid}")
    public ResponseEntity<PaymentModel> updatePayment(@PathVariable("paymentid") Long paymentid, @RequestBody PaymentModel paymentModel) {
        Optional<PaymentModel> paymentData = paymentRepository.findById(paymentid);

        if (paymentData.isPresent()) {
            PaymentModel updatedPayment = paymentData.get();
            updatedPayment.setAmount(paymentModel.getAmount());
            updatedPayment.setDateTime(paymentModel.getDateTime());
            updatedPayment.setPaymentMethod(paymentModel.getPaymentMethod());
            updatedPayment.setInvoice(paymentModel.getInvoice());
            updatedPayment.setUpdatedAt(new Date());
            return new ResponseEntity<>(paymentRepository.save(updatedPayment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idp}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable("idp") Long idp) {
        try {
            paymentRepository.deleteById(idp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
